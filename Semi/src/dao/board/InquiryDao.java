package dao.board;

import java.util.List;

import dto.board.Inquiry;
import utils.Paging;



public interface InquiryDao {
	
	// Inquiry 전체 페이징하여 리스트로 조회 
	public List<Inquiry> selectPagingList(Paging paging);
	
	// 전체 게시글 수 조회
	public int selectCntAll() ;
		
	// Inq_idx로 하나의 Inquiry 조회하기
	public Inquiry selectInqByInqIdx (Inquiry inq);
	
	// 조회후 조회수 올리기
	public void updateHit(Inquiry inq);
	
	// 게시물 등록 하기
	public void insert(Inquiry inq);
	
	// 게시물 삭제 하기
	public void delete(Inquiry inq);
	
	// 게시물 수정 하기
	public void update(Inquiry inq);
	
	// 답변 안된 게시글 조회 
	public List<Inquiry> selectInqByAnswer();
}
