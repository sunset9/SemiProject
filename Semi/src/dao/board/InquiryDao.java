package dao.board;

import java.util.List;

import dto.board.Inquiry;
import utils.Paging;



public interface InquiryDao {
	
	// 페이징 된 Inquiry 리스트 조회
	public List<Inquiry> selectPagingList(Paging paging);
	
	// 전체 게시물 수 조회
	public int selectCntAll() ;
		
	// inq_idx로 문의 사항 조회하기
	public Inquiry selectInqByInqIdx (Inquiry inq);
	
	// 상세보기 후 조회수 증가 시키기
	public void updateHit(Inquiry inq);
	
	// 문의 사항 추가하기
	public void insert(Inquiry inq);
	
	// 문의 사항 삭제 
	public void delete(Inquiry inq);
	
	// 문의 사항 수정
	public void update(Inquiry inq);
	
	// 답변안된 문의사항 리스트 불러오기
	public List<Inquiry> selectInqByAnswer();
	
	// nickname 조회하기
	public String selectEmailByInq_idx(Inquiry inq);
	
	// email 조회하기
	public String selectNickByInq_idx(Inquiry inq);
}
