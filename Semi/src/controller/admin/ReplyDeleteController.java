package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Reply;
import service.reply.ReplyService;
import service.reply.ReplyServiceImpl;



@WebServlet("/reply/delete")

public class ReplyDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ReplyService replyService = new ReplyServiceImpl();
	private Reply reply = new Reply();
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파라미터 얻기
		replyService.getParam(req, resp);
		
		// 댓글 삭제하기
		replyService.replyDelete(reply);
		
		
		resp.sendRedirect("");
	}
   

}
