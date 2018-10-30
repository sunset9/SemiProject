package service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Qna;
import dto.board.QnaFile;
import utils.Paging;

public interface AdminQnaService {


	// 현재 페이지 얻어오기 
	public int getCurPage(HttpServletRequest req);
	
	// 전체 게시물 수 얻어오기
	public int getTotalCount(String search);
	
	// 검색어 얻기 
	public String getSearch(HttpServletRequest req);
	
	// 게시물 페이징 리스트 얻어오기
	public List<Qna> getPagingList(Paging paging);
	
	// 게시물 상세보기
	public Qna view (Qna qna);
		
	// 게시물 파라미터 얻어오기 
	public Qna getParam (HttpServletRequest res, HttpServletResponse resp);
	
	// Qna 쓰기 
	// 게시글 내용과 첨부파일 함께 업로드
	public void write(HttpServletRequest req);
	
	// Qna 수정
	public void update(HttpServletRequest req);
		
	// Qna 삭제
	public void delete(Qna qna);
	
	// 파일 상세보기
	public QnaFile viewFile(Qna qna);
	
	// 작성자 아이디 조회 
	public String getId(Qna qna);
	
	// 작성자 닉네임 조회 
	public String getNick(Qna qna);
	
	// 게시글 리스트로 삭제 
	public void qnaListDelete(String names);
	
	// 로그인 체크하기
	public boolean loginCheck(HttpServletRequest req);
	
	
}
