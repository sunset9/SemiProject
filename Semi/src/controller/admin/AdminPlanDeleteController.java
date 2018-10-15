package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.plan.Plan;
import service.plan.AdminPlanService;
import service.plan.AdminPlanServiceImpl;
import service.timetable.TimetableService;
import service.timetable.TimetableServiceImpl;

@WebServlet("/admin/plan/delete")
public class AdminPlanDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminPlanService adminPlanService = new AdminPlanServiceImpl();
	private TimetableService ttService = new TimetableServiceImpl(); 
	private Plan plan = new Plan();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 요청 파라미터 받기
		plan = adminPlanService.getParam(req);
		
		// 일정 삭제 
		adminPlanService.delete(plan);
		
		// 일정 삭제
	
		// 연관된 타임테이블 삭제
		ttService.delete(plan);
		
		req.getRequestDispatcher("")
		.forward(req, resp);
	}
}
