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
	public int getTotalCount();
	
	// 게시물 페이징 리스트 얻어오기
	public List<Qna> getPagingList(Paging paging);
	
	// 게시물 상세보기
	public Qna view (Qna qna);
		
	// 게시물 파라미터 얻어오기 
	public Qna getParam (HttpServletRequest res, HttpServletResponse resp);
	
	// Qna 쓰기 
	public void write(Qna qna);
	
	// Qna 수정
	public void update(Qna qna);
		
	// Qna 삭제
	public void delete(Qna qna);
	
	// Qna 파일 삭제 
	public void deleteQnaFile(QnaFile file);
	
	// 파일 상세보기
	public QnaFile viewFile(Qna qna);
	
	// 파일 넣기
	public void insertFile(QnaFile file);
	
}
