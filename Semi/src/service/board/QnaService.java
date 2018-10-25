package service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Qna;
import dto.board.QnaFile;
import utils.Paging;

public interface QnaService {


	// 현재 페이지 얻어오기
	public int getCurPage(HttpServletRequest req);
	
	// 전체 게시물 카운트
	public int getTotalCount(String search);
	
	// 페이징 된 리스트 불러오기 
	public List<Qna> getPagingList(Paging paging);
	
	// 게시물 상세 보기
	public Qna view (Qna qna);
		
	// 파라미터 얻어오기 
	public Qna getParam (HttpServletRequest req, HttpServletResponse resp);
	
	// 검색어 얻기 
	public String getSearch(HttpServletRequest req);
	
	//게시물에 관련된 파일 불러오기
	public QnaFile viewFile(Qna qna);
	
	// 게시물 작성자 아이디 얻어오기 
	public String getId(Qna qna);
	
}
