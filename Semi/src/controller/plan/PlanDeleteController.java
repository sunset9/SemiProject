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
import service.stroy.StoryService;
import service.stroy.StoryServiceImpl;
import service.timetable.TimetableService;
import service.timetable.TimetableServiceImpl;

@WebServlet("/plan/delete")
public class PlanDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	PlanService pService = new PlanServiceImpl();
	TimetableService ttbService = new TimetableServiceImpl();
	StoryService sService = new StoryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println();
		System.out.println("----- PlanDeleteController -----");
		
		//요청 파라미터 처리
		Plan planParam = pService.getParam(req);
		System.out.println(planParam);
		//연관된 타임테이블 삭제
		ttbService.deleteTimetable(planParam);
		
		//연관된 스토리 삭제
		sService.deleteStory(planParam);
		
		//일정 삭제 
		pService.deletePlan(planParam);
		
		resp.sendRedirect("/user/myPage");
	}
}
