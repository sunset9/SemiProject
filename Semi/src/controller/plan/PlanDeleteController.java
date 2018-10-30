package controller.plan;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.plan.Plan;
import service.plan.PlanService;
import service.plan.PlanServiceImpl;
import service.timetable.TimetableService;
import service.timetable.TimetableServiceImpl;

@WebServlet("/PlanDeleteController")
public class PlanDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	PlanService pService = new PlanServiceImpl();
	TimetableService ttService = new TimetableServiceImpl(); 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청파라미터 -> Plan
		Plan plan = pService.getSession4Plan(req);
		
		// 일정 삭제
		pService.delete(plan);
		// 연관된 타임테이블 삭제
		ttService.delete(plan);
		
		req.getRequestDispatcher("")
		.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//요청 파라미터 처리
		int param = Integer.parseInt(req.getParameter("plan_idx"));
		System.out.println("planDeleteController : "+param);
		
		//plan 모델 
		Plan plan = new Plan();
		plan.setPlan_idx(param);
		
		//일정 삭제 
		pService.delete(plan);
		
		//연관된 타임테이블 삭제
		ttService.delete(plan);
		
		resp.sendRedirect("/user/myPage");
	}
}
