package service.board;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.board.InqFileDao;
import dao.board.InqFileDaoImpl;
import dao.board.InquiryDao;
import dao.board.InquiryDaoImpl;
import dao.board.ReplyDao;
import dao.board.ReplyDaoImpl;
import dto.board.InqFile;
import dto.board.Inquiry;
import dto.board.Reply;
import utils.Paging;

public class InquiryServiceImpl implements InquiryService {
	private InquiryDao inquiryDao= new InquiryDaoImpl(); 
	private InqFileDao fileDao = new InqFileDaoImpl();
	private ReplyDao replyDao = new ReplyDaoImpl();
	
	@Override
	public Inquiry getParam(HttpServletRequest req, HttpServletResponse resp) {
		
		// 파라미터 담을 객체 만들기
		Inquiry inquiry = new Inquiry();
		
		// 요청에 있는 inq넘버 받아오기
		String inq_idx = req.getParameter("inq_idx");
		
		// inq_idx 가 null이나 빈값이 아닐때 DTO에 저장하기
		if(inq_idx != null && !"".equals(inq_idx)) {
			inquiry.setInq_idx(Integer.parseInt(inq_idx));
		}
//		System.out.println("inqservice inq: "+ inquiry);
		// DTO 객체 반환하기
		return inquiry;
	}

	@Override
	public int getCurPage(HttpServletRequest req) {
		
		// 요청 파라미터 받아오기
		String curPage = req.getParameter("curPage");
		
		// null이나 공백이 아닐 때 변수값 반환하기
		if( curPage != null && !"".equals(curPage)) {
			return Integer.parseInt(curPage);
		}
		
		// null이나 공백일 때 0 반환
		return 0;
	}

	@Override
	public int getTotalCount(String search) {
		return inquiryDao.selectCntAll(search);
	}

	@Override
	public List<Inquiry> getPagingList(Paging paging) {
		
		return inquiryDao.selectPagingList(paging);
	}

	@Override
	public Inquiry view(Inquiry inq) {
		Inquiry inquiry = new Inquiry();
		inquiry = inquiryDao.selectInqByInqIdx(inq);
		inquiryDao.updateHit(inq);
		
		return inquiry;
	}

	@Override
	public void update(HttpServletRequest req) {
		Inquiry inq = null;
		InqFile inqFile = null;
		

		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		
		if(!isMultipart) {
			//파일 첨부가 없을 경우
			inq = new Inquiry();
			
			inq.setTitle(req.getParameter("title"));
			inq.setWriter((String) req.getSession().getAttribute("writerNick"));
			inq.setContent(req.getParameter("content"));
			
		} else {
			//파일업로드를 사용하고 있을 경우
			inq = new Inquiry();

			//디스크팩토리
			DiskFileItemFactory factory = new DiskFileItemFactory();

			//메모리처리 사이즈
			factory.setSizeThreshold(1 * 1024 * 1024); //1MB

			//임시 저장소
			File repository=new File(req.getServletContext().getRealPath("tmp"));
			factory.setRepository(repository);
			
			//업로드 객체 생성
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			//용량 제한 설정 : 10MB
			upload.setFileSizeMax(10 * 1024 * 1024);
			
			//한글 처리
			upload.setHeaderEncoding("utf-8");

			//form-data 추출 
			List<FileItem> items = null;
			try {
				items = upload.parseRequest(req);
				
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			
			//파싱된 데이터 처리 반복자
			Iterator<FileItem> iter = items.iterator();
			
			//요청정보 처리
			while( iter.hasNext() ) {
				FileItem item = iter.next();
				
				// 빈 파일 처리
				if( item.getSize() <= 0 )	continue;
				
				// 빈 파일이 아닐 경우
				if( item.isFormField() ) {
					
					if( "inq_idx".equals( item.getFieldName() ) ) {
						try {
							inq.setInq_idx( Integer.parseInt(item.getString("utf-8")) );
						} catch (NumberFormatException | UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}

					try {
						if( "title".equals( item.getFieldName() ) ) {
							inq.setTitle( item.getString("utf-8") );
						}
						if( "content".equals( item.getFieldName() ) ) {
							inq.setContent( item.getString("utf-8") );
						}
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					
					inq.setWriter((String) req.getSession().getAttribute("userid"));
					
				} else {
					UUID uuid = UUID.randomUUID();
//					System.out.println(uuid);
					
					String u = uuid.toString().split("-")[4];
//					System.out.println(u);
					// -----------------
					
					//로컬 저장소 파일
					String stored = item.getName() + "_" + u;
					File up = new File(
						req.getServletContext().getRealPath("upload/inquiry")
						, stored);
					
						
					inqFile = new InqFile();
					inqFile.setOrigin_name(item.getName());
					inqFile.setStored_name(stored);
					inqFile.setFile_size(item.getSize());
					
					try {
						// 실제 업로드
						item.write(up);
						
						// 임시 파일 삭제
						item.delete();
						
					} catch (Exception e) {
						e.printStackTrace();
					} // try end
				} //if end
			} //while end
		} //if(!isMultipart) end
		

//		System.out.println(board);
//		System.out.println(boardFile);
		
		if(inq != null) {
			inquiryDao.update(inq);
		}
		
		if(inqFile != null) {
			inqFile.setInq_idx(inq.getInq_idx());
			fileDao.insert(inqFile);
		}
	}

	@Override
	public void delete(Inquiry inq) {
		inquiryDao.delete(inq);
		fileDao.delete(inq);
	}

	@Override
	public InqFile viewFile(Inquiry inq) {
		
		return fileDao.selectByInqIdx(inq);
	}

	@Override
	public void insertFile(InqFile file) {
		fileDao.insert(file);
	}

	@Override
	public String getId(Inquiry inq) {
		return inquiryDao.selectIdByInq_idx(inq);
	}

	@Override
	public String getNick(Inquiry inq) {
		return inquiryDao.selectNickByInq_idx(inq);
	}

	@Override
	public void write(HttpServletRequest req) {
		Inquiry inquiry = null;
		InqFile inqFile = null;
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		
		if(!isMultipart) {
			// 첨부 파일이 없을 경우
			inquiry = new Inquiry();

			inquiry.setTitle(req.getParameter("title"));
			inquiry.setContent(req.getParameter("content"));
			inquiry.setWriter((String) req.getSession().getAttribute("nickname"));
			inquiry.setInq_idx((int)req.getSession().getAttribute("user_idx"));
			
			System.out.println("첨부파일 없을 경우 ="+req.getSession().getAttribute("user_idx"));
			
		} else {
			//파일 업로드 사용할 경우 
			inquiry = new Inquiry();
			
			// 디스크 팩토리 
			DiskFileItemFactory factory = new DiskFileItemFactory(); 
			
			// 메모리처리 사이즈
			factory.setSizeThreshold(1*1024*1024); // 1MB
			
			// 임시 저장소 
			File repository = new File(req.getServletContext().getRealPath("tmp"));
			factory.setRepository(repository);
			
			// 업로드 객체 생성 
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			// 용량 제한 설정: 10MB
			upload.setFileSizeMax(10*1024*1024);
			
			//form-data 추출 
			List<FileItem> items = null;
			try {
				items = upload.parseRequest (req);
			
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			
			// 파싱된 데이터 처리 반복자 
			Iterator<FileItem> iter = items.iterator();
			
			// 요청 정보 처리 
			while (iter.hasNext()) {
				FileItem item = iter.next();
				
				// 빈 파일 처리
				if(item.getSize() <= 0) continue;
				
				// 빈 파일 아닐 경우
				if(item.isFormField()) {
					try {
						if("title".equals (item.getFieldName() ) ) {
								inquiry.setTitle(item.getString("utf-8"));
						}
						if("content".equals( item.getFieldName())) {
							inquiry.setContent(item.getString("utf-8"));
						}
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					
					inquiry.setWriter((String)req.getSession().getAttribute("nickname"));
					inquiry.setUser_idx((int)req.getSession().getAttribute("user_idx"));
					
					System.out.println("첨부파일 있는 경우 ="+req.getSession().getAttribute("user_idx"));
					
				} else {
					UUID uuid = UUID.randomUUID();
					String u = uuid.toString().split("-")[4];
					
					// 로컬 저장소 파일
					String stored = item.getName()+"_"+u;
					File up = new File(req.getServletContext().getRealPath("upload/inquiry"),stored);
					
					inqFile = new InqFile();
					inqFile.setOrigin_name(item.getName());
					inqFile.setStored_name(stored);
					inqFile.setFile_size(item.getSize());
					
					try {
						// 실제 업로드
						item.write(up);
						
						// 임시 파일 삭제
						item.delete();
						
					} catch (Exception e) {
						e.printStackTrace();
					} // try end
				} // if end
			}// while end
		} // if(!isMultipart) end 
		
		int inq_idx = inquiryDao.selectInqIdx();
		
		if(inquiry !=null) {
			inquiry.setInq_idx(inq_idx);
//			System.out.println("insert 호출 직전" + inquiry);
			inquiryDao.insert(inquiry);
		}
		if (inqFile != null) {
			inqFile.setInq_idx(inq_idx);
			fileDao.insert(inqFile);
		}
		
	}

	@Override
	public boolean checkWriter(HttpServletRequest req, Inquiry inq) {

		int loginId = (int) req.getSession().getAttribute("user_idx");
		Inquiry inquiry= inquiryDao.selectInqByInqIdx(inq);
		
		if(loginId != inquiry.getUser_idx()) {
			return false;
		}
		return true;
	}

	@Override
	public String getSearch(HttpServletRequest req) {
		String search = req.getParameter("search");

		return search;
	}

	@Override
	public void insertRepley(Reply reply) {
		replyDao.insertReply(reply);
	}

	@Override
	public boolean deleteReply(Reply reply) {
		replyDao.delete(reply);
		
		if(replyDao.countReply(reply) > 0) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public void answerOk(Reply reply) {
//		System.out.println("answer 실행 쓰?");
		replyDao.answerUpdate(reply);
	}







}
