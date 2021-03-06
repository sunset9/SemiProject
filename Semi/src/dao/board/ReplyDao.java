package dao.board;

import java.util.List;

import dto.board.Inquiry;
import dto.board.Reply;

public interface ReplyDao {

			
		// 댓글 목록 불러오기 
		public List<Reply> selectInqByInqIdx (Inquiry inquiry);
		
		
		// 댓글 추가하기 
		public void insertReply(Reply reply);
		
		// 댓글 하나 삭제 
		public void delete(Reply reply);
		
		// 게시물 삭제시 댓글 삭제
		public void deleteByInq(Inquiry inq);
		
		// 댓글 카운트 - 댓글 존재 여부 확인 
		public int countReply (Reply reply);
		
		// 댓글 달리면 답변 예정 -> 답변 완료하기
		public void answerUpdate(Reply reply);
}
