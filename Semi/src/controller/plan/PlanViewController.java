package controller.plan;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/PlanViewController")
public class PlanViewController extends HttpServlet {
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
		List<Story> storyList = sService.getStoryList(plan);

		// 스토리 댓글 리스트 가져오기
		List<Comment> commList = sService.getCommentList(storyList);
		
		// 가계부 정보 가져오기
		Account acc = pService.getAccount(plan);
		
		// 플랜 저장
		pService.write(plan);
		
		// view 폼 띄우기
		req.getRequestDispatcher("").forward(req, resp);
	}
}
