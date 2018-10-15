package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Qna;
import service.board.AdminQnaService;
import service.board.AdminQnaServiceImpl;
import service.board.QnaService;
import service.board.QnaServiceImpl;



@WebServlet("admin/qna/view")
public class AdminQnaViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminQnaService adminQnaService = new AdminQnaServiceImpl();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		// �Ķ���� �ޱ� 
		Qna qna = adminQnaService.getParam(req, resp);

		// �Խù� �� ����
		qna = adminQnaService.view(qna);

		// ���� ����
		req.setAttribute("qna", qna);
		
		
		req.getRequestDispatcher("").forward(req, resp);
	}
}
