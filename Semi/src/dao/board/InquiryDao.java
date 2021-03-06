package dao.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.board.Inquiry;
import utils.Paging;



public interface InquiryDao {
	
	// 페이징 된 Inquiry 리스트 조회
	public List<Inquiry> selectPagingList(Paging paging);
	
	// 전체 게시물 수 조회
	public int selectCntAll(String search) ;
		
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
	public List<Inquiry> selectInqByAnswer(Paging paging);
	
	// nickname 조회하기
	public String selectIdByInq_idx(Inquiry inq);
	
	// email 조회하기
	public String selectNickByInq_idx(Inquiry inq);
	
	// 다음 게시글 번호 조회쿼리
	public int selectInqIdx();
	
	// 리스트에서 게시글 삭제
	public void deleteInqList(String names);
	
	// myPage에서 사용하는 내가 질문한 문의사항 조회
	public List<Inquiry> selectPagingMyList(Paging paging, int user_idx);
}
