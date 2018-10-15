package controller.plan;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Account.Account;
import dto.plan.Plan;
import dto.plan.User;
import dto.story.Comment;
import dto.story.Story;
import dto.timetable.Location;
import dto.timetable.Timetable;
import service.plan.PlanService;
import service.plan.PlanServiceImpl;
import service.stroy.StoryService;
import service.stroy.StoryServiceImpl;
import service.timetable.TimetableService;
import service.timetable.TimetableServiceImpl;

/**
 * Servlet implementation class PlanUpdateController
 */
@WebServlet("/PlanUpdateController")
public class PlanUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PlanService pService = new PlanServiceImpl();
	TimetableService ttService = new TimetableServiceImpl();
	StoryService sService = new StoryServiceImpl();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청파라미터(plan_idx) -> Plan 모델 
		Plan param = pService.getParam(req);
		
		// 일정 기본 정보 가져오기
		Plan plan = pService.getPlanInfo(param);
		
		// 유저 정보 가져오기
		User user = pService.getUserInfo(plan);
		
		// 타임테이블 리스트 가져오기
		List<Timetable> timetableList = ttService.getTimetableList(plan);
		
		// 장소정보(타임테이블에 등록한) 리스트 가져오기
		List<Location> locList = ttService.getLocationList(plan);
		
		// 스토리 리스트 가져오기
		List<Story> storyList = sService.getStoryList(param);

		// 스토리 댓글 리스트 가져오기
		List<Comment> commList = sService.getCommentList(storyList);
		
		// 가계부 정보 가져오기
		Account acc = pService.getAccount(param);

		// 업데이트 뷰 폼 띄워주기 
		req.getRequestDispatcher("")
		.forward(req, resp);
		}
	
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// 플랜 정보 파라미터 받기 
			Plan plan = pService.getParam(req);
			Map<Timetable, Location> ttLoc = ttService.getParam(req);
			
			// 타임테이블 업데이트
			ttService.update(plan, ttLoc);
			
			// 글 정보 업데이트
			pService.update(plan);
			
			req.getRequestDispatcher("").forward(req, resp);
			
		}
		 
}
