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

		
		// 로그인 확인
		boolean check = adminNoticeService.loginCheck(req);
				
		if(!check) {
			resp.sendRedirect("/user/login");
			return;
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
