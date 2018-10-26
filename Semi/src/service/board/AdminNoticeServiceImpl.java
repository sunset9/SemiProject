package service.board;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.board.NoticeDao;
import dao.board.NoticeDaoImpl;
import dao.board.NoticeFileDao;
import dao.board.NoticeFileDaoImpl;
import dto.board.Notice;
import dto.board.NoticeFile;
import utils.Paging;

public class AdminNoticeServiceImpl implements AdminNoticeService{
	private NoticeDao noticeDao = new NoticeDaoImpl();
	private NoticeFileDao fileDao = new NoticeFileDaoImpl();
	
	
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
		return noticeDao.selectCntAll(search);
	}
	
	@Override
	public List<Notice> getPagingList(Paging paging) {
		return noticeDao.selectPagingList(paging);
	}
	
	@Override
	public Notice view(Notice notice) {
		Notice noti = new Notice();
		noticeDao.updateHit(notice);
		noti = noticeDao.selectNoticeByNoticeIdx(notice);
		return noti;
	}
	
	@Override
	public String getSearch(HttpServletRequest req) {
		String search = req.getParameter("search");

		return search;
	}
	
	@Override
	public Notice getParam(HttpServletRequest req, HttpServletResponse resp) {

		// 파라미터 담을 객체 
		Notice notice = new Notice();
		
		// 요청에 있는 notice_idx 받아오기 
		String notice_idx = req.getParameter("notice_idx");
		
		// notice_idx 가 null이나 빈값이 아닐때 DTO에 저장하기
		if(notice_idx !=null && !"".equals(notice_idx)) {
			notice.setNotice_idx(Integer.parseInt(notice_idx));
		}
		
		return notice;
	}
	
	@Override
	public NoticeFile viewFile(Notice notice) {

		return fileDao.selectFileByNotice_idx(notice);
	}
	
	@Override
	public void write(HttpServletRequest req) {
		Notice notice = null;
		NoticeFile noticeFile = null;
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if(!isMultipart) {
			// 첨부파일 없을 경우
		} else {
			// 파일 업로드 사용한 경우 
			notice = new Notice();
			
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
							notice.setTitle(item.getString("utf-8"));
						}
						if("content".equals(item.getFieldName())) {
							notice.setContent(item.getString("utf-8"));
						}
						
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					
					notice.setWriter((String)req.getSession().getAttribute("nickname"));
					notice.setUser_idx((int)req.getSession().getAttribute("user_idx"));
				}
			}
			
		}
		
	}
	@Override
	public void update(HttpServletRequest req) {
		
	}
	@Override
	public void delete(Notice notice) {
		noticeDao.delete(notice);
		fileDao.delete(notice);
	}



}
