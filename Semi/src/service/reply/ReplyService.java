package service.reply;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Reply;

public interface ReplyService {
	
	// 파라미터 얻어오기 
	public Reply getParam (HttpServletRequest req, HttpServletResponse resp);
	
	// 댓글 삭제 하기 
	public void replyDelete(Reply reply);
	
	// 댓글 쓰기
	public void replyWrite(Reply reply);
	
	
}
