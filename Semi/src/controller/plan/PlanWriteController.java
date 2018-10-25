package controller.plan;

import java.io.IOException;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.plan.Plan;
import dto.user.User;
import dto.timetable.Location;
import dto.timetable.Timetable;
import service.plan.PlanService;
import service.plan.PlanServiceImpl;
import service.timetable.TimetableService;
import service.timetable.TimetableServiceImpl;

/**
 * Servlet implementation class PlanSaveController
 */
@WebServlet("/plan/write")
public class PlanWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PlanService pService = new PlanServiceImpl();
	TimetableService ttService = new TimetableServiceImpl(); 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 뷰 지정
		req.getRequestDispatcher("/plan/view.jsp")
		.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// 요청 파라미터 받아오기
		Plan plan = pService.getParam4Edit(req);
		Map<Timetable, Location> ttLoc = ttService.getParam(req);
		
		// 타임테이블 정보 저장
		ttService.write(plan,ttLoc);
		// 일정 정보 저장하기
		pService.write(plan);
		
		resp.sendRedirect("/plan");
	}
	
}
