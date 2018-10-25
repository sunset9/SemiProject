package service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.InqFile;
import dto.board.Inquiry;
import dto.board.Reply;
import utils.Paging;

public interface AdminInquiryService {

 
	// 현재 페이지 얻어오기
	public int getCurPage(HttpServletRequest req);
	
	// 검색어 얻어 오기 
	public String getSearch(HttpServletRequest req);
	
	// 게시물 전체 수 얻어오기
	public int getTotalCount(String search);
	
	// 문의사항 리스트 페이징 얻어오기
	public List<Inquiry> getPagingList(Paging paging);
	
	// 메인 화면에서 필요한 
	// 문의사항 답변 안된 리스트 페이징 얻어오기
	public List<Inquiry> getMainPagingList(Paging paging);
	
	// 문의사항 상세 보기
	public Inquiry view (Inquiry inq);
	
	// 문의사항 파라미터 얻어오기 
	public Inquiry getParam (HttpServletRequest req, HttpServletResponse resp);
	
	// 문의사항 삭제하기 
	public void delete(Inquiry inq);
	
	// 문의사항 관련 파일 삭제
	public void deleteInqFile(InqFile file);
	
	// 	문의사항 파일 불러오기 
	public InqFile viewFile(Inquiry inq);

	// 첨부파일 추가
	public void insertFile(InqFile file);

	// 글쓴 사람 아이디 조회 하기
	public String getId(Inquiry inq);
		
	// 글쓴 사람 닉네임 조회하기
	public String getNick(Inquiry inq);
		
	// 삭제하기 전에 글 작성자인지 판단 
	public boolean checkWriter(HttpServletRequest req, Inquiry inq);
		
	// 댓글 추가하기 
	public void insertRepley(Reply reply);
		
	// 댓글 추가되면 답변 예정 -> 답변 완료로 변경 하기 
	public void answerOk(Reply reply);
		
	// 댓글 삭제하기
	public boolean deleteReply(Reply reply);
	
}
