package controller.plan;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dto.Account.Account;
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
	TimetableService ttbService = new TimetableServiceImpl(); 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 요청파라미터(plan_idx) -> Plan 모델
		Plan param = pService.getParam(req);
		
		// 일정 기본 정보 가져오기
		Plan planView = pService.getPlanInfo(param);
		
		GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm");
        Gson gson = gsonBuilder.create();
		
		//planView MODEL 전달
		req.setAttribute("planView", planView);
		// 유저 정보 가져오기
		User userView = pService.getUserInfo(planView);
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
				
		// 뷰 지정
		req.getRequestDispatcher("/plan/view.jsp")
		.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		// 요청 파라미터 받아오기
		//Plan plan = pService.getParam4Edit(req);
		Map<Timetable, Location> ttLoc = ttService.getParam(req);
		
		// 요청파라미터(plan_idx) -> Plan 모델
		// param을 받아와야 함
		Plan param = pService.getParam(req);
		System.out.println("플랜라이트 컨트롤러 : "+param);
		
		
		// 일정 기본 정보 가져오기
		Plan planView = pService.getPlanInfo(param);
		// System.out.println("플랜뷰컨트롤러 planView : "+planView); --> 지은 확인
		System.out.println("플랜라이트 컨트롤러 : "+planView);
		
		// planView MODEL 전달
		req.setAttribute("planView", planView);
		
		// 타임테이블 정보 저장
//		ttService.write(plan,ttLoc);
		// 일정 정보 저장하기
//		pService.write(plan);
		
	resp.sendRedirect("/plan");
	}
	
}
