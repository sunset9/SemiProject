package service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.InqFile;
import dto.board.Inquiry;
import utils.Paging;

public interface AdminInquiryService {

 
	// 현재 페이지 얻어오기
	public int getCurPage(HttpServletRequest req);
	
	// 게시물 전체 수 얻어오기
	public int getTotalCount();
	
	// 문의사항 리스트 페이징 얻어오기
	public List<Inquiry> getPagingList(Paging paging);
	
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

	// 답변 안된 문의사항 불러오기
	public List<Inquiry> notAnswerList ();
	
}
