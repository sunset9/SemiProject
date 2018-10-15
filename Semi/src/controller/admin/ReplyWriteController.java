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

@WebServlet("/reply/write")
public class ReplyWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	private ReplyService replyService = new ReplyServiceImpl();
	private Reply reply = new Reply();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		reply = replyService.getParam(req, resp);
		replyService.replyWrite(reply);
	
	}
}
