package controller.inquiry;

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
import service.board.InquiryService;
import service.board.InquiryServiceImpl;


@WebServlet("/inquiry/view")
public class InqViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InquiryService inquiryService = new InquiryServiceImpl();
	private ReplyDao replyDao = new ReplyDaoImpl();
	private InqFileDao fileDao = new InqFileDaoImpl();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		// 파라미터 받아오기
		Inquiry inquiry = inquiryService.getParam(req, resp);

		
		// 상세화면 보기 
		inquiry = inquiryService.view(inquiry);
		
		// 댓글 목록 불러오기
		List<Reply> list = replyDao.selectInqByInqIdx(inquiry);
		
		// 파일 목록 불러오기
		fileDao.selectByInqIdx(inquiry);
		
		// 모델에 담아서 전달하기 
		req.setAttribute("inquiry", inquiry);
		
		
		req.getRequestDispatcher("").forward(req, resp);
	}
}
