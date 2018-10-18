package service.board;

import java.io.File;
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
import dto.board.InqFile;
import dto.board.Inquiry;
import utils.Paging;

public class InquiryServiceImpl implements InquiryService {
	private InquiryDao inquiryDao= new InquiryDaoImpl(); 
	private InqFileDao fileDao = new InqFileDaoImpl();
	
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
	public int getTotalCount() {
		return inquiryDao.selectCntAll();
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
	public void update(Inquiry inq) {
		inquiryDao.update(inq);
	}

	@Override
	public void delete(Inquiry inq) {
		inquiryDao.delete(inq);
	}

	@Override
	public void deleteInqFile(InqFile file) {
		
		fileDao.delete(file);
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
	public String getEmail(Inquiry inq) {
		return inquiryDao.selectEmailByInq_idx(inq);
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
					if("title".equals (item.getFieldName() ) ) {
						inquiry.setTitle(item.getString());
					}
					if("content".equals( item.getFieldName())) {
						inquiry.setContent(item.getString());
					}
					
					inquiry.setWriter((String)req.getSession().getAttribute("nickname"));
					inquiry.setUser_idx((int)req.getSession().getAttribute("user_idx"));
					
					System.out.println("첨부파일 있는 경우 ="+req.getSession().getAttribute("user_idx"));
					
				} else {
					UUID uuid = UUID.randomUUID();
					String u = uuid.toString().split("-")[4];
					
					// 로컬 저장소 파일
					String stored = item.getName()+"_"+u;
					File up = new File(req.getServletContext().getRealPath("upload"),stored);
					
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
			System.out.println("insert 호출 직전" + inquiry);
			inquiryDao.insert(inquiry);
		}
		if (inqFile != null) {
			inqFile.setInq_idx(inq_idx);
			fileDao.insert(inqFile);
		}
		
	}



}
