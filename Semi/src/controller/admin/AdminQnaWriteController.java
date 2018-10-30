package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.board.Qna;
import service.board.AdminQnaService;
import service.board.AdminQnaServiceImpl;
import service.board.QnaService;
import service.board.QnaServiceImpl;



@WebServlet("/admin/qna/write")
public class AdminQnaWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private AdminQnaService adminQnaService = new AdminQnaServiceImpl();
	private Qna qna = new Qna();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 로그인 확인
		boolean check = adminQnaService.loginCheck(req);
				
		if(!check) {
			resp.sendRedirect("/user/login");
			return;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

		// 게시글 작성
		adminQnaService.write(req);

		// view 페이지 지정
		resp.sendRedirect("/admin/qna/list");
	
	
	}
}
