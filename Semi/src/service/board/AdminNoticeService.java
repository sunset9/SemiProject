package service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Notice;
import dto.board.NoticeFile;
import utils.Paging;

public interface AdminNoticeService {


	// 현재 페이지 불러오기 
	public int getCurPage(HttpServletRequest req);
	
	// 전체 게시물 수 얻어오기 
	public int getTotalCount();
	
	// 공지사항 리스트 페이징
	public List<Notice> getPagingList(Paging paging);
	
	// 공지사항 상세보기 
	public Notice view (Notice notice);
	
	// 공지사항 파라미터 얻어오기
	public Notice getParam (HttpServletRequest res, HttpServletResponse resp);
	
	// 공지사항 쓰기 
	public void write(Notice notice);
	
	// 공지사항 업데이트 
	public void update(Notice notice);
		
	// 공지사항 지우기 
	public void delete(Notice notice);
	
	// 공지사항 파일 지우기
	public void deleteNoticeFile(NoticeFile file);
		
	// 공지사항 파일 보기
	public NoticeFile viewFile(Notice notice);
		
	// 공지사항 파일 넣기
	public void insertFile(NoticeFile file);
}
