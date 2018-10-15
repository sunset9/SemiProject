package dao.board;

import java.util.List;

import dto.board.Inquiry;
import dto.board.Reply;

public interface ReplyDao {

			
		// inq_idx 로댓글 불러오기
		public List<Reply> selectInqByInqIdx (Inquiry inquiry);
		
		
		// 댓글등록 하기
		public void insert(Reply reply);
		
		// 댓글 삭제 하기
		public void delete(Reply reply);
		

}
