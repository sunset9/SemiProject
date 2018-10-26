package controller.plan;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dto.Account.Account;
import dto.plan.Plan;
import dto.timetable.Location;
import dto.timetable.Timetable;
import dto.user.User;
import service.plan.PlanService;
import service.plan.PlanServiceImpl;
import service.stroy.StoryService;
import service.stroy.StoryServiceImpl;
import service.timetable.TimetableService;
import service.timetable.TimetableServiceImpl;

@WebServlet("/plan")
public class PlanViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PlanService pService = new PlanServiceImpl();
	TimetableService ttbService = new TimetableServiceImpl();
	StoryService sService = new StoryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm");
        Gson gson = gsonBuilder.create();
        
		// 요청파라미터(plan_idx) -> Plan 모델 
		// param을 받아와야 함
		Plan param = pService.getParam(req);
		
		// 일정 기본 정보 가져오기
		Plan planView = pService.getPlanInfo(param);
		//System.out.println("플랜뷰컨트롤러 planView : "+planView); --> 지은 확인
		
		//planView MODEL 전달
		req.setAttribute("planView", planView);
		
		// 유저 정보 가져오기
		User userView = pService.getUserInfo(planView);
		System.out.println("플랜뷰컨트롤러 userView : "+userView);
		
		//userView MODEL 전달
		req.setAttribute("userView", userView);
		
		// timetable, location 리스트 받기
		List<Timetable> ttbList = ttbService.getTimetableList(planView);
		List<Location> locList = ttbService.getLocationList(planView);
		
		// timetable 과 location이 1:1 대응하지 않는 경우 (DB데이터 문제)
		if(ttbList.size() != locList.size()) {
			System.out.println("[ERR] 타임테이블과 위치정보의 개수가 일치하지 않습니다.");
			return;
		}
		
		// JSON 형태로 변환
		String ttbListStr = gson.toJson(ttbList);
		String locListStr = gson.toJson(locList);
		
		// 파라미터 지정
		req.setAttribute("ttbList", ttbListStr);
		req.setAttribute("locList", locListStr);
		
		// 스토리 리스트 가져오기
		//List<Story> storyList = sService.getStoryList(plan);

		// 스토리 댓글 리스트 가져오기
		//List<Comment> commList = sService.getCommentList(storyList);
		
		// 가계부 정보 가져오기
		Account accView = pService.getAccount(planView);
		//accView MODEL 전달
		req.setAttribute("accView", accView);
		
		// 플랜 저장
		//pService.write(plan);
		
		// view 폼 띄우기
		req.getRequestDispatcher("/plan/view.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm");
        Gson gson = gsonBuilder.create();
        
		// 요청파라미터(plan_idx) -> Plan 모델 
		// param을 받아와야 함
		//Plan param = pService.getParam(req);
		int plan_idx = Integer.parseInt(req.getParameter("plan_idx"));
		System.out.println("플랜뷰컨트롤러 plan_idx : "+plan_idx);
		Plan param = new Plan();
		param.setPlan_idx(plan_idx);
		
		// 일정 기본 정보 가져오기
		Plan planView = pService.getPlanInfo(param);
		
		//planView MODEL 전달
		req.setAttribute("planView", planView);
		
		// 유저 정보 가져오기
		User userView = pService.getUserInfo(planView);
		System.out.println("플랜뷰컨트롤러 userView : "+userView);
		
		//userView MODEL 전달
		req.setAttribute("userView", userView);
		
		// timetable, location 리스트 받기
		List<Timetable> ttbList = ttbService.getTimetableList(planView);
		List<Location> locList = ttbService.getLocationList(planView);
		
		// timetable 과 location이 1:1 대응하지 않는 경우 (DB데이터 문제)
		if(ttbList.size() != locList.size()) {
			System.out.println("[ERR] 타임테이블과 위치정보의 개수가 일치하지 않습니다.");
			return;
		}
		
		// JSON 형태로 변환
		String ttbListStr = gson.toJson(ttbList);
		String locListStr = gson.toJson(locList);
		
		// 파라미터 지정
		req.setAttribute("ttbList", ttbListStr);
		req.setAttribute("locList", locListStr);
		
		// 스토리 리스트 가져오기
		//List<Story> storyList = sService.getStoryList(plan);

		// 스토리 댓글 리스트 가져오기
		//List<Comment> commList = sService.getCommentList(storyList);
		
		// 가계부 정보 가져오기
		Account accView = pService.getAccount(planView);
		//accView MODEL 전달
		req.setAttribute("accView", accView);
		
		// 플랜 저장
		//pService.write(plan);
		
		// view 폼 띄우기
		req.getRequestDispatcher("/plan/view.jsp").forward(req, resp);
	}
}
