package service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.board.NoticeDao;
import dao.board.NoticeDaoImpl;
import dao.board.NoticeFileDao;
import dao.board.NoticeFileDaoImpl;
import dto.board.Notice;
import dto.board.NoticeFile;
import utils.Paging;

public class NoticeServiceImpl implements NoticeService{
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
	public Notice getParam(HttpServletRequest req, HttpServletResponse resp) {
		
		// 파라미터 담을 객체 생성 
		Notice notice = new Notice();
		
		// 요청에 있는 notice 넘버 받아오기 
		String notice_idx = req.getParameter("notice_idx");
		
		// notice_idx가 null이나 빈값이 아닐 때 DTO에 저장하기 
		if(notice_idx != null && !"".equals(notice_idx)) {
			notice.setNotice_idx(Integer.parseInt(notice_idx));
		}
		
		// DTO 객체 반환하기
		return notice;
	}


	@Override
	public NoticeFile viewFile(Notice notice) {
		return fileDao.selectFileByNotice_idx(notice);
	}

	@Override
	public int getTotalCount(String search) {
		return noticeDao.selectCntAll(search);
	}

	@Override
	public String getSearch(HttpServletRequest req) {
		String search = req.getParameter("search");
		
		return search;
	}

	@Override
	public String getId(Notice notice) {
		return noticeDao.selectIdByNoticeIdx(notice);
	}


}
