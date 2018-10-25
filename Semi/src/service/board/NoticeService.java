package service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Notice;
import dto.board.NoticeFile;
import utils.Paging;

public interface NoticeService {


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
	
	// 게시물 작성자 아이디 얻어 오기
	public String getId(Notice notice);

	


}
