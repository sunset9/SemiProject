package dao.board;

import java.util.List;

import dto.board.Qna;
import utils.Paging;

public interface QnaDao {

		// Qna 전체 페이징하여 리스트로 조회 
		public List<Qna> selectPagingList(Paging paging);
		
		// 전체 게시글 수 조회
		public int selectCntAll() ;
			
		// Qna_idx로 하나의 Qna 조회하기
		public Qna selectInqByInqIdx (Qna qna);
		
		// 조회후 조회수 올리기
		public void updateHit(Qna qna);
		
		// 게시물 등록 하기
		public void insert(Qna qna);
		
		// 게시물 삭제 하기
		public void delete(Qna qna);
		
		// 게시물 수정 하기
		public void update(Qna qna);
}
