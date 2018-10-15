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

@WebServlet("/admin/plan/list")
public class AdminPlanListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminPlanService adminPlanService = new AdminPlanServiceImpl();
	private Plan plan = new Plan();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		// 일정 조회
		List<Plan> pList = adminPlanService.selectPlanAll();
		
	
		req.getRequestDispatcher("")
		.forward(req, resp);
	}
}
