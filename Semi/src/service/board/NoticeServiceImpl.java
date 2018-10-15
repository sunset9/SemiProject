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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Notice> getPagingList(Paging paging) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notice view(Notice notice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notice getParam(HttpServletRequest res, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public NoticeFile viewFile(Notice notice) {
		// TODO Auto-generated method stub
		return null;
	}


}
