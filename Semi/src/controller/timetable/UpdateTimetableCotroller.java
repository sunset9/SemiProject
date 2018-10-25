package controller.timetable;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.plan.Plan;
import dto.timetable.Location;
import dto.timetable.Timetable;
import service.timetable.TimetableService;
import service.timetable.TimetableServiceImpl;

@WebServlet("/update/ttb")
public class UpdateTimetableCotroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	TimetableService ttbService = new TimetableServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		// plan param은 어디서 받을지 아직 몰라서 우선 임의로 여기서 만들어줌
		Plan planParam = new Plan();
		planParam.setPlan_idx(Integer.parseInt(req.getParameter("plan_idx")));
		
		// 요청파라미터 -> Map 타입
		Map<Timetable, Location> ttbLocParam = ttbService.getParam(req);
		
		// timetable, location 정보 업데이트
		ttbService.update(planParam, ttbLocParam);
		
		resp.sendRedirect("/plan");
	}
}
