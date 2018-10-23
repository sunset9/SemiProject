package dao.board;

import java.util.List;

import dto.board.Inquiry;
import dto.board.Reply;

public interface ReplyDao {

			
		// inq_idx �δ�� �ҷ�����
		public List<Reply> selectInqByInqIdx (Inquiry inquiry);
		
		
		// 댓글 추가하기 
		public void insertReply(Reply reply);
		
		// ��� ���� �ϱ�
		public void delete(Reply reply);
		

}
