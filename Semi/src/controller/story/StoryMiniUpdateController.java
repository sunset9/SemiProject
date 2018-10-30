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
		
		// 요청 파라미터 추출
		Story storyParam = sService.getParam(req);
		Map<Timetable, Location> ttbLocParam = ttbService.getMiniParam(req);
		System.out.println("----- 1.스토리 서비스 param -----");
		System.out.println(storyParam);
		System.out.println(ttbLocParam);
		
		// ttb_idx 가 없는 경우 seq.nextval 조회해서 얻어옴
		int ttb_idx = ttbService.getMiniTtbIdx(ttbLocParam);
		System.out.println("||||ttb_idx = " + ttb_idx);
		boolean isStory = ttbService.isStory(ttb_idx);
		System.out.println("||||isStory = " + isStory);
		
		// 미니뷰 스토리 저장
		sService.writeMini(ttb_idx, storyParam, isStory);
		
		// 해당 타임테이블 저장
		ttbService.writeTtb(ttb_idx, ttbLocParam);
		
//		 미니뷰 업데이트 성공 시 ajax응답
//		if(isSucc) {
		System.out.println("----- 4. [miniViewupdate]성공, ajax 측 응답 -----");
		resp.getWriter().println(ttb_idx);
//		}
	}
}

