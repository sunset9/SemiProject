package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Qna;
import service.board.AdminQnaService;
import service.board.AdminQnaServiceImpl;
import utils.Paging;



@WebServlet("admin/qna/list")
public class AdminQnaListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminQnaService adminQnaService = new AdminQnaServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		int curPage = adminQnaService.getCurPage(req);
		
	
		int totalCount = adminQnaService.getTotalCount();
		
		Paging paging = new Paging(totalCount, curPage,10); 
		
	
		List<Qna> list = adminQnaService.getPagingList(paging);
		
		
		req.setAttribute("qnalist", list);
		
		
		req.setAttribute("paging", paging);
		
		
		req.getRequestDispatcher("").forward(req, resp);
		
		
	}
}


































