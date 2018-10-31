package controller.bookmark;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.bookmark.Bookmark;
import dto.plan.Plan;
import service.bookmark.BookmarkService;
import service.bookmark.BookmarkServiceImpl;
import service.plan.PlanService;
import service.plan.PlanServiceImpl;

/**
 * Servlet implementation class PlanUpdateController
 */
@WebServlet("/bookmark/insert")
public class BookmarkInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PlanService pService = new PlanServiceImpl();
	BookmarkService bService = new BookmarkServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println();
		System.out.println("----- BookmarkInsertController -----");
		req.setCharacterEncoding("utf-8");
		
		// 플랜 정보 파라미터 받기 
		Plan planParam = pService.getParam(req);
		
		Plan planView = pService.getPlanInfo(planParam);
		System.out.println(planParam);
		
		bService.insertBookmark(planView);
		
		Bookmark book = bService.getBookmarkInfo(planParam);
		System.out.println(book);
		
		resp.sendRedirect("/plan?plan_idx="+planParam.getPlan_idx());
	}
}
