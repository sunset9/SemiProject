package controller.reply;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Reply;
import service.board.InquiryService;
import service.board.InquiryServiceImpl;


@WebServlet("/reply/delete")
public class ReplyDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private InquiryService inquiryService = new InquiryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Reply reply = new Reply();
		
		String rep_idx = req.getParameter("rep_idx");
		reply.setRep_idx(Integer.parseInt(rep_idx));
		
//		System.out.println("rep_idx?" +rep_idx);
		
		boolean success = inquiryService.deleteReply(reply);
		
		System.out.println("success ?" + success);
		
		resp.getWriter().append("{\"success\":"+success+"}");
	}
}
