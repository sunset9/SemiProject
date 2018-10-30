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
import dto.board.InqFile;
import dto.board.Inquiry;
import dto.board.Reply;
import service.board.AdminInquiryService;
import service.board.AdminInquiryServiceImpl;
import service.board.InquiryService;
import service.board.InquiryServiceImpl;


@WebServlet("/admin/inquiry/view")
public class AdminInqViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminInquiryService admininquiryService = new AdminInquiryServiceImpl();
	private ReplyDao replyDao = new ReplyDaoImpl();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		// 로그인 확인
		boolean check = admininquiryService.loginCheck(req);
				
		if(!check) {
			resp.sendRedirect("/user/login");
			return;
		}
		// 요청 파라미터 얻기
		Inquiry inquiry = admininquiryService.getParam(req, resp);

		// 게시글 상세 조회  
		inquiry = admininquiryService.view(inquiry);
		
		// 게시글에 관련된 댓글 불러오기
		List<Reply> list = replyDao.selectInqByInqIdx(inquiry);
		
		// 게시글에 관련된 첨부파일 조회해오기 
		InqFile inqFile = admininquiryService.viewFile(inquiry);
		
		// 요청에 조회한 결과 보내기
		req.setAttribute("inquiry", inquiry);
		req.setAttribute("inqFile", inqFile);
		req.setAttribute("replyList", list);
		
		req.getRequestDispatcher("/admin/inquiry/inqView.jsp").forward(req, resp);
	}
}
