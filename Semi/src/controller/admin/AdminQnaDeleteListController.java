package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.board.AdminQnaService;
import service.board.AdminQnaServiceImpl;


@WebServlet("/admin/qna/deleteList")
public class AdminQnaDeleteListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminQnaService adminQnaService = new AdminQnaServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String names = req.getParameter("names");
		
//		System.out.println("실행쓰?"+names);
		
		if(!"".equals(names) && names != null) {
			adminQnaService.qnaListDelete(names);
		}
		
		resp.sendRedirect("/admin/qna/list");
	}
}
