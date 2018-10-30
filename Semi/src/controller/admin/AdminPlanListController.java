package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.plan.Plan;
import service.plan.AdminPlanService;
import service.plan.AdminPlanServiceImpl;
import utils.Paging;

@WebServlet("/admin/plan/list")
public class AdminPlanListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminPlanService adminPlanService = new AdminPlanServiceImpl();
	private Plan plan = new Plan();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 확인
//		boolean check = adminPlanService.loginCheck(req);
//				
//		if(!check) {
//			resp.sendRedirect("/user/login");
//			return;
//		}
		
		// 현재 페이지 얻어오기 
//		int curPage = adminPlanService.getCurPage(req);
		
		// 검색어 얻어오기 
//		String search = adminPlanService.getSearch(req);
		
		// 전체 페이지 얻어오기 
//		int totalCount = adminPlanService.getTotalCount(search);
		
		// 페이징 객체 생성 
//		Paging paging = new Paging(totalCount, curPage);
		
		// 페이징 객체에 검색어 적용 
//		paging.setSearch(search);
//		
		// 게시글 리스트 조회 
//		List<Plan> list = adminPlanService.getPaingList(paging);
		
		// 요청에 조회 결과 담기 
//		req.setAttribute("planList", list);
		
		// 페이징 결과 요청에 담기 
//		req.setAttribute("paging", paging);
		
		// view 페이지 지정
		req.getRequestDispatcher("/admin/plan/plist.jsp").forward(req, resp);
	}
}
