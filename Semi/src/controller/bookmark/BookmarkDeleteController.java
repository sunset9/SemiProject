package controller.bookmark;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.user.Bookmark;
import service.bookmark.BookmarkService;
import service.bookmark.BookmarkServiceImpl;
import service.plan.PlanService;
import service.plan.PlanServiceImpl;

/**
 * Servlet implementation class PlanUpdateController
 */
@WebServlet("/bookmark/delete")
public class BookmarkDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PlanService pService = new PlanServiceImpl();
	BookmarkService bService = new BookmarkServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println();
		System.out.println("----- BookmarkDeleteController -----");
		req.setCharacterEncoding("utf-8");
		
		// 플랜 정보 파라미터 받기 
		Bookmark book = bService.getParam(req);
		System.out.println(book);
		
		bService.deleteBookmark(book);
		System.out.println(book);
		
		resp.sendRedirect("/plan?plan_idx="+req.getParameter("plan_idx"));
	}
}
