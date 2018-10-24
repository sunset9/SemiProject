package service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Notice;
import dto.board.NoticeFile;
import utils.Paging;

public interface AdminNoticeService {

	// 현재 페이지 얻어오기 
	public int getCurPage(HttpServletRequest req);
		
	// 전체 게시물 카운트 
	public int getTotalCount(String search);
	
	// 페이징 된 리스트 불러오기
	public List<Notice> getPagingList(Paging paging);

	// 게시물 상세 보기 
	public Notice view (Notice notice);

	// 검색어 얻기 
	public String getSearch(HttpServletRequest req);
		
	// 파라미터 얻어오기
	public Notice getParam (HttpServletRequest req, HttpServletResponse resp);
		
	// 게시물에 관련된 파일 불러오기 
	public NoticeFile viewFile(Notice notice);
		
	
	// 공지사항 쓰기
	// 게시글 내용과 첨부파일 함께 업로드
	public void write(HttpServletRequest req);
	
	// 공지사항 업데이트 
	public void update(HttpServletRequest req);
		
	// 공지사항 지우기 
	// 게시물에 관련된 파일도 같이 삭제
	public void delete(Notice notice);
	
}
