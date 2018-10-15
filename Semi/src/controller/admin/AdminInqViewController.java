package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.board.InqFileDao;
import dao.board.InqFileDaoImpl;
import dao.board.ReplyDao;
import dao.board.ReplyDaoImpl;
import dto.board.Inquiry;
import dto.board.Reply;
import service.board.AdminInquiryService;
import service.board.AdminInquiryServiceImpl;
import service.board.InquiryService;
import service.board.InquiryServiceImpl;


@WebServlet("/inquiry/view")
public class AdminInqViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminInquiryService admininquiryService = new AdminInquiryServiceImpl();
	private ReplyDao replyDao = new ReplyDaoImpl();
	private InqFileDao fileDao = new InqFileDaoImpl();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		// �Ķ���� �޾ƿ���
		Inquiry inquiry = admininquiryService.getParam(req, resp);

		
		// ��ȭ�� ���� 
		inquiry = admininquiryService.view(inquiry);
		
		// ��� ��� �ҷ�����
		List<Reply> list = replyDao.selectInqByInqIdx(inquiry);
		
		// ���� ��� �ҷ�����
		fileDao.selectByInqIdx(inquiry);
		
		// �𵨿� ��Ƽ� �����ϱ� 
		req.setAttribute("inquiry", inquiry);
		
		
		req.getRequestDispatcher("").forward(req, resp);
	}
}
