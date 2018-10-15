package controller.inquiry;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.board.InqFileDao;
import dao.board.InqFileDaoImpl;
import dto.board.InqFile;
import dto.board.Inquiry;
import dto.board.Reply;
import service.board.InquiryService;
import service.board.InquiryServiceImpl;
import service.reply.ReplyService;
import service.reply.ReplyServiceImpl;


@WebServlet("/inquiry/delete")
public class InqDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private InquiryService inquiryService = new InquiryServiceImpl();
	private Inquiry inquiry = new Inquiry();
	private InqFile file = new InqFile();
	private ReplyService replyService= new ReplyServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// �Ķ���� �޾ƿ���
		inquiry = inquiryService.getParam(req, resp);
		
		// �����ϱ�
		inquiryService.delete(inquiry);
		
		// ���� �𵨿� �Խù� ��ȣ �ֱ�
		file.setInq_idx(inquiry.getInq_idx());
		
		// �Խù��� ���õ� ���� �����
		inquiryService.deleteInqFile(file);
		
		// ��ۿ� �Խù� ��ȣ �ֱ� 
		Reply reply = new Reply();
		reply.setInq_idx(inquiry.getInq_idx());
		
		// �Խù��� ���õ� ��� �����
		replyService.replyDelete(reply);
		
		
		
		resp.sendRedirect("");
	}
   

}
