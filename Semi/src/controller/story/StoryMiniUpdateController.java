package controller.story;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.plan.Plan;
import dto.story.Story;
import dto.timetable.Location;
import dto.timetable.Timetable;
import service.plan.PlanService;
import service.plan.PlanServiceImpl;
import service.stroy.StoryService;
import service.stroy.StoryServiceImpl;
import service.timetable.TimetableService;
import service.timetable.TimetableServiceImpl;

@WebServlet("/story/mini/update")
public class StoryMiniUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PlanService pService = new PlanServiceImpl();
	StoryService sService = new StoryServiceImpl();
	TimetableService ttbService = new TimetableServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		// 해당 스토리 우선 저장(ttb_idx -> nextval 조회해서 얻어옴)
		boolean isSucc = sService.updateMini(req);
		
		// 미니뷰 업데이트 성공 시 ajax응답
		if(isSucc) {
			System.out.println("----- 4. [miniViewupdate]성공, ajax 측 응답 -----");
			resp.getWriter().println(true);
		}
	}
}

