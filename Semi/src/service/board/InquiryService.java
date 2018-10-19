package service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.InqFile;
import dto.board.Inquiry;
import utils.Paging;

public interface InquiryService {


	// 현재 페이지 얻어오기 
	public int getCurPage(HttpServletRequest req);
	
	// 전체 게시물 수 얻어오기
	public int getTotalCount();
	
	// 페이징 된 리스트 불러오기
	public List<Inquiry> getPagingList(Paging paging);
	
	// 상세 페이지 조회
	public Inquiry view (Inquiry inq);
	
	// 파라미터 얻어오기 
	public Inquiry getParam (HttpServletRequest req, HttpServletResponse resp);
	
	// 문의사항 쓰기 
	// 게시글 내용과 첨부파일을 함께 업로드
	public void write(HttpServletRequest req);
	
	// 문의사항 수정
	public void update(Inquiry inq);
	
	// 문의사항 삭제 
	public void delete(Inquiry inq);
	
	
	// 상세보기에서 관련된 첨부파일 조회
	public InqFile viewFile(Inquiry inq);
	
	// 첨부파일 추가
	public void insertFile(InqFile file);

	// 글쓴 사람 아이디 조회 하기
	public String getId(Inquiry inq);
	
	// 글쓴 사람 닉네임 조회하기
	public String getNick(Inquiry inq);
	
	// 삭제하기 전에 글 작성자인지 판단 
	public boolean checkWriter(HttpServletRequest req, Inquiry inq);
	
}
