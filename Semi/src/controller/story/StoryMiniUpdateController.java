package controller.story;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

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
		resp.setContentType("text/html;charset=utf-8");
		
		// 요청 파라미터 추출
		Story storyParam = sService.getParam(req);
		Map<Timetable, Location> ttbLocParam = ttbService.getMiniParam(req);
		
		// ttb_idx 가 없는 경우 seq.nextval 조회해서 얻어옴
		int ttb_idx = ttbService.getMiniTtbIdx(ttbLocParam);
		boolean isStory = ttbService.isStory(ttb_idx);
		
		// 미니뷰 스토리 저장
		sService.writeMini(ttb_idx, storyParam, isStory);
		
		// 해당 타임테이블 저장
		ttbService.writeTtb(ttb_idx, ttbLocParam);
		
		
		// 미니뷰 업데이트 성공 시 ajax응답 - 저장한 타임테이블 idx값 넘겨줌
		Gson gson = new Gson();
		
		JsonObject obj = new JsonObject();
		obj.addProperty("ttb_idx", ttb_idx);
		String jsonStr = gson.toJson(obj);
		
		resp.getWriter().println(jsonStr);
		
	}
}

