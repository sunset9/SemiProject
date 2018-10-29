package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.board.Notice;
import service.board.AdminNoticeService;
import service.board.AdminNoticeServiceImpl;
import service.board.NoticeService;
import service.board.NoticeServiceImpl;



@WebServlet("/admin/notice/write")
public class AdminNoticeWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private AdminNoticeService adminNoticeService = new AdminNoticeServiceImpl();
	private Notice notice = new Notice();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		System.out.println("login : ?"+req.getSession().getAttribute("login"));
		// 로그인 되어있지 않으면 main 으로 리다이렉트 하기
		if( req.getSession().getAttribute("login")==null) {
			resp.sendRedirect("/main");
		}
		req.getRequestDispatcher("/admin/notice/write.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		// 게시글 작성 하기
		adminNoticeService.write(req);
		
		// view 페이지 지정
		resp.sendRedirect("/admin/notice/list");
	
	
	}
}
