package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.InqFile;
import dto.board.Inquiry;
import dto.board.Reply;
import service.admin.AdminService;
import service.admin.AdminServiceImpl;
import service.board.AdminInquiryService;
import service.board.AdminInquiryServiceImpl;
import service.reply.ReplyService;
import service.reply.ReplyServiceImpl;


@WebServlet("admin/inquiry/delete")
public class AdminInqDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminInquiryService adminInquiryService = new AdminInquiryServiceImpl();
	private Inquiry inquiry = new Inquiry();
	private InqFile file = new InqFile();
	private ReplyService replyService = new ReplyServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파라미터 값 받아오기
		inquiry = adminInquiryService.getParam(req, resp);
		
		// 게시물 삭제 하기
		adminInquiryService.delete(inquiry);
		
		// 파일에 게시물 인덱스 지정해주기 
		file.setInq_idx(inquiry.getInq_idx());
		
		// 게시물에 관련된 파일 지우기 
		adminInquiryService.deleteInqFile(file);
		
		//게시물에 관련된 댓글에 게시물 번호 지정해주기
		Reply reply = new Reply();
		reply.setInq_idx(inquiry.getInq_idx());
		
		// 게시물에 관련된 댓글 지우기
		replyService.replyDelete(reply);
		
		
		
		resp.sendRedirect("");
	}
   

}
