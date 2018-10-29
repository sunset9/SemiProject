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

import dao.board.QnaDao;
import dao.board.QnaDaoImpl;
import dao.board.QnaFileDao;
import dao.board.QnaFileDaoImpl;
import dto.board.Qna;
import dto.board.QnaFile;
import utils.Paging;

public class AdminQnaServiceImpl implements  AdminQnaService{

	private QnaDao qnaDao = new QnaDaoImpl();
	private QnaFileDao fileDao  = new QnaFileDaoImpl();
	
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
		return qnaDao.selectCntAll(search);
	}
	
	@Override
	public String getSearch(HttpServletRequest req) {
		String search = req.getParameter("search");

		return search;
	}
	@Override
	public List<Qna> getPagingList(Paging paging) {
		return qnaDao.selectPagingList(paging);
	}
	
	@Override
	public Qna view(Qna qna) {
		Qna q = new Qna();
		qnaDao.updateHit(qna);
		q = qnaDao.selectQnaByQnaIdx(qna);
		return q;
	}
	
	@Override
	public Qna getParam(HttpServletRequest req, HttpServletResponse resp) {
		Qna qna = new Qna();
		
		String qna_idx = req.getParameter("qna_idx");
		
		if(qna_idx !=null && !"".equals(qna_idx)) {
			qna.setQna_idx(Integer.parseInt(qna_idx));
		}
		
		return qna;
	}
	
	@Override
	public void write(HttpServletRequest req) {
		Qna qna = null;
		QnaFile qnaFile = null;
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if(!isMultipart) {
			// 첨부파일 없을 경우
		} else {
			// 파일 업로드 사용한 경우 
			qna = new Qna();
			
			// 디스크 팩토리 
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			// 메모리 처리 사이즈 
			factory.setSizeThreshold(1*1024*1024); 
			
			// 임시 저장소
			File repository = new File(req.getServletContext().getRealPath("tmp"));
			factory.setRepository(repository);
			
			// 업로드 객체 생성
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			// 용량 제한 설정 
			upload.setFileSizeMax(10*1024*1024);
			
			// form-data 추출 
			List<FileItem> items = null;
			
			try {
				items = upload.parseRequest(req);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			
			// 파싱된 데이터 처리 반복자 
			Iterator<FileItem> iter = items.iterator();
			
			// 요청 정보 처리 
			while(iter.hasNext()) {
				FileItem item = iter.next();
				
				//빈 파일 처리 
				if (item.getSize()<=0) continue;
				
				// 빈 파일이 아닐 경우 
				if( item.isFormField()) {
					try {
						if("title".equals(item.getFieldName())) {
							qna.setTitle(item.getString("utf-8"));
						}
						if("content".equals(item.getFieldName())) {
							qna.setContent(item.getString("utf-8"));
						}
						
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					
					qna.setWriter((String)req.getSession().getAttribute("nickname"));
					qna.setUser_idx((int)req.getSession().getAttribute("user_idx"));
				}else {
					UUID uuid = UUID.randomUUID();
//					System.out.println(uuid);
					
					String u = uuid.toString().split("-")[4];
//					System.out.println(u);
					// -----------------
					
					//로컬 저장소 파일
					String stored = item.getName() + "_" + u;
					File up = new File(
						req.getServletContext().getRealPath("upload/qna")
						, stored);
					
						
					qnaFile = new QnaFile();
					qnaFile.setOrigin_name(item.getName());
					qnaFile.setStored_name(stored);
					qnaFile.setFile_size(item.getSize());
					
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
		

		int qna_idx = qnaDao.selectQnaIdx();
		
		if(qna != null) {
			qna.setQna_idx(qna_idx);
			qnaDao.insert(qna);
		}
		
		if(qnaFile != null) {
			qnaFile.setQna_idx(qna.getQna_idx());
			fileDao.insert(qnaFile);
		}		
		
		
	}
	@Override
	public void update(HttpServletRequest req) {
		Qna qna = null;
		QnaFile qnaFile = null;
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if(!isMultipart) {
			// 첨부파일 없을 경우
		} else {
			// 파일 업로드 사용한 경우 
			qna = new Qna();
			
			// 디스크 팩토리 
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			// 메모리 처리 사이즈 
			factory.setSizeThreshold(1*1024*1024); 
			
			// 임시 저장소
			File repository = new File(req.getServletContext().getRealPath("tmp"));
			factory.setRepository(repository);
			
			// 업로드 객체 생성
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			// 용량 제한 설정 
			upload.setFileSizeMax(10*1024*1024);
			
			// form-data 추출 
			List<FileItem> items = null;
			
			try {
				items = upload.parseRequest(req);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
			
			// 파싱된 데이터 처리 반복자 
			Iterator<FileItem> iter = items.iterator();
			
			// 요청 정보 처리 
			while(iter.hasNext()) {
				FileItem item = iter.next();
				
				//빈 파일 처리 
				if (item.getSize()<=0) continue;
				
				// 빈 파일이 아닐 경우 
				if( item.isFormField()) {
					if("qna_idx".equals( item.getFieldName())) {
						try {
							qna.setQna_idx(Integer.parseInt(item.getString("utf-8")));
						} catch (NumberFormatException | UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					}
					try {
						if("title".equals(item.getFieldName())) {
							qna.setTitle(item.getString("utf-8"));
						}
						if("content".equals(item.getFieldName())) {
							qna.setContent(item.getString("utf-8"));
						}
						
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					
					qna.setWriter((String)req.getSession().getAttribute("nickname"));
				}else {
					UUID uuid = UUID.randomUUID();
//					System.out.println(uuid);
					
					String u = uuid.toString().split("-")[4];
//					System.out.println(u);
					// -----------------
					
					//로컬 저장소 파일
					String stored = item.getName() + "_" + u;
					File up = new File(
						req.getServletContext().getRealPath("upload/qna")
						, stored);
					
						
					qnaFile = new QnaFile();
					qnaFile.setOrigin_name(item.getName());
					qnaFile.setStored_name(stored);
					qnaFile.setFile_size(item.getSize());
					
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
		

		if(qna != null) {
			qnaDao.insert(qna);
		}
		
		if(qnaFile != null) {
			qnaFile.setQna_idx(qna.getQna_idx());
			fileDao.insert(qnaFile);
		}		
		
	}
	@Override
	public void delete(Qna qna) {
		fileDao.delete(qna);
		qnaDao.delete(qna);
		
		
	}
	@Override
	public QnaFile viewFile(Qna qna) {
		return null;
	}
	@Override
	public String getId(Qna qna) {
		return qnaDao.selectIdByQnaIdx(qna);
	}
	@Override
	public String getNick(Qna qna) {
		return qnaDao.selectNickByQna(qna);
	}
	
	@Override
	public void qnaListDelete(String names) {
		fileDao.deleteQnaListFile(names);
		qnaDao.deleteQnaList(names);
		
	}
	


}
