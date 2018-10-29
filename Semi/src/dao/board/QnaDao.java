package dao.board;

import java.util.List;

import dto.board.Qna;
import utils.Paging;

public interface QnaDao {

		// 페이징 된 Qna 리스트 조회 
		public List<Qna> selectPagingList(Paging paging);
		
		// 전체 게시물 수 조회
		public int selectCntAll( String search) ;
			
		// Qna_idx로 게시물 조회 
		public Qna selectQnaByQnaIdx (Qna qna);
		
		// 조회수 올리기
		public void updateHit(Qna qna);
		
		// 게시물 추가하기
		public void insert(Qna qna);
		
		// 게시물 삭제
		public void delete(Qna qna);
		
		// 게시물 수정
		public void update(Qna qna);
		
		// notice_idx로 작성자 id 조회하기
		public String selectIdByQnaIdx(Qna qna);
		
		// nickname 조회하기 
		public String selectNickByQna (Qna qna);
		
		// 다음 게시글 번호 조회 쿼리
		public int selectQnaIdx();
		
		// 리스트에서 게시글 삭제 
		public void deleteQnaList(String names);
}
