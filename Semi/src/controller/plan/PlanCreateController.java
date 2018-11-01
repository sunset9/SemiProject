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
@WebServlet("/plan/create")
public class PlanCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	PlanService pService = new PlanServiceImpl();

	TimetableService ttbService = new TimetableServiceImpl(); 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println();
		System.out.println("----- PlanCreateController -----");
		// 한글 인코딩
		req.setCharacterEncoding("UTF-8");
		
		GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm");
        Gson gson = gsonBuilder.create();

//				// 요청 파라미터 받아오기
		Map<Timetable, Location> ttLoc = ttbService.getParam(req);
		
		// 요청 파라미터 처리
		Plan param = pService.getParamCreate(req);
		//System.out.println("플랜 라이트 컨트롤러 : "+ param);
		
		// user_idx 구하기
		User cUser = (User) req.getSession().getAttribute("user");
		User cUserSocial = (User) req.getSession().getAttribute("socialUser");
		
		if(cUserSocial == null) {
			System.out.println("아이디 로그인 유저");
			//일정 정보 등록하기
			pService.createPlan(param, cUser);
			
			//등록한 일정 정보 plan_idx가져오기
			int plan_idx = pService.getPlan_idx();
			System.out.println(plan_idx);
			
			// 일정 기본 정보 가져오기
			Plan planView = pService.getPlanInfo(plan_idx);
			System.out.println(planView);
			// planView MODEL 전달
			req.setAttribute("planView", planView);
	
			// 게시자 유저 정보 가져오기
			User writtenUserView = pService.getUserInfo(planView);
			//userView MODEL 전달
			req.setAttribute("writtenUserView", writtenUserView);
			System.out.println(writtenUserView);
			
//			---------------------로그인 유저 파라미터 가져오기
			// 요청파라미터(user_idx) -> Plan 모델
			User userParam = pService.getSessionUser(req);
			// 로그인 유저 정보 가져오기
			User loginedUserView = pService.getUserInfoLogin(userParam);
			//userView MODEL 전달
			req.setAttribute("loginedUserView", loginedUserView);
			System.out.println("로그인 유저 : " + loginedUserView);
			
			// timetable, location 리스트 받기
			List<Timetable> ttbList = ttbService.getTimetableList(planView);
			List<Location> locList = ttbService.getLocationList(planView, ttbList);
			
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
			
			// 가계부 정보 가져오기
			Account accView = pService.getAccount(planView);
			//accView MODEL 전달
			req.setAttribute("accView", accView);

			
		} else if(cUser == null) {
			System.out.println("소셜 로그인 유저");
			//새 일정 등록 
			pService.createPlan(param, cUserSocial);
			
			// insert한 plan의 plan_idx 가져오기
			int plan_idx = pService.getPlan_idx();
			System.out.println("plan_idx : " + plan_idx);
			
			// 일정 기본 정보 가져오기
			Plan planView = pService.getPlanInfo(plan_idx);
			System.out.println(planView);
			// planView MODEL 전달
			req.setAttribute("planView", planView);

			// 게시자 유저 정보 가져오기
			User writtenUserView = pService.getUserInfo(planView);
			// userView MODEL 전달
			req.setAttribute("writtenUserView", writtenUserView);
			System.out.println(writtenUserView);

//						---------------------로그인 유저 파라미터 가져오기
			// 요청파라미터(user_idx) -> Plan 모델
			User userParam = pService.getSessionUser(req);
			// 로그인 유저 정보 가져오기
			User loginedUserView = pService.getUserInfoLogin(userParam);
			// userView MODEL 전달
			req.setAttribute("loginedUserView", loginedUserView);
			System.out.println("로그인 유저 : " + loginedUserView);

			// timetable, location 리스트 받기
			List<Timetable> ttbList = ttbService.getTimetableList(planView);
			List<Location> locList = ttbService.getLocationList(planView, ttbList);

			// timetable 과 location이 1:1 대응하지 않는 경우 (DB데이터 문제)
			if (ttbList.size() != locList.size()) {
				System.out.println("[ERR] 타임테이블과 위치정보의 개수가 일치하지 않습니다.");
				return;
			}

			// JSON 형태로 변환
			String ttbListStr = gson.toJson(ttbList);
			String locListStr = gson.toJson(locList);

			// 파라미터 지정
			req.setAttribute("ttbList", ttbListStr);
			req.setAttribute("locList", locListStr);

			// 가계부 정보 가져오기
			Account accView = pService.getAccount(planView);
			// accView MODEL 전달
			req.setAttribute("accView", accView);

			//plan_idx 세션에 추가 
			//req.getSession().setAttribute("plan_idx", plan_idx);
		}
		
		// 뷰 지정
		req.getRequestDispatcher("/plan/write.jsp")
		.forward(req, resp);
	}

	TimetableService ttbService = new TimetableServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println();
		System.out.println("----- PlanCreateController -----");
		// 한글 인코딩
		req.setCharacterEncoding("UTF-8");
		
		GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm");
        Gson gson = gsonBuilder.create();

//				// 요청 파라미터 받아오기
		Map<Timetable, Location> ttLoc = ttbService.getParam(req);
		
		// 요청 파라미터 처리
		Plan param = pService.getParamCreate(req);
		//System.out.println("플랜 라이트 컨트롤러 : "+ param);
		
		// user_idx 구하기
		User cUser = (User) req.getSession().getAttribute("user");
		User cUserSocial = (User) req.getSession().getAttribute("socialUser");
		
		if(cUserSocial == null) {
			System.out.println("아이디 로그인 유저");
			//일정 정보 등록하기
			pService.createPlan(param, cUser);
			
			//등록한 일정 정보 plan_idx가져오기
			int plan_idx = pService.getPlan_idx();
			
			// 일정 기본 정보 가져오기
			Plan planView = pService.getPlanInfo(plan_idx);
			System.out.println(planView);
			// planView MODEL 전달
			req.setAttribute("planView", planView);
	
			// 게시자 유저 정보 가져오기
			User writtenUserView = pService.getUserInfo(planView);
			//userView MODEL 전달
			req.setAttribute("writtenUserView", writtenUserView);
			System.out.println(writtenUserView);
			
//			---------------------로그인 유저 파라미터 가져오기
			// 요청파라미터(user_idx) -> Plan 모델
			User userParam = pService.getSessionUser(req);
			// 로그인 유저 정보 가져오기
			User loginedUserView = pService.getUserInfoLogin(userParam);
			//userView MODEL 전달
			req.setAttribute("loginedUserView", loginedUserView);
			System.out.println("로그인 유저 : " + loginedUserView);
			
			// timetable, location 리스트 받기
			List<Timetable> ttbList = ttbService.getTimetableList(planView);
			List<Location> locList = ttbService.getLocationList(planView, ttbList);
			
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
			
			// 가계부 정보 가져오기
			Account accView = pService.getAccount(planView);
			//accView MODEL 전달
			req.setAttribute("accView", accView);

			
		} else if(cUser == null) {
			System.out.println("소셜 로그인 유저");
			//새 일정 등록 
			pService.createPlan(param, cUserSocial);
			
			// insert한 plan의 plan_idx 가져오기
			int plan_idx = pService.getPlan_idx();
			System.out.println("plan_idx : " + plan_idx);
			
			// 일정 기본 정보 가져오기
			Plan planView = pService.getPlanInfo(plan_idx);
			System.out.println(planView);
			// planView MODEL 전달
			req.setAttribute("planView", planView);

			// 게시자 유저 정보 가져오기
			User writtenUserView = pService.getUserInfo(planView);
			// userView MODEL 전달
			req.setAttribute("writtenUserView", writtenUserView);
			System.out.println(writtenUserView);

//									---------------------로그인 유저 파라미터 가져오기
			// 요청파라미터(user_idx) -> Plan 모델
			User userParam = pService.getSessionUser(req);
			// 로그인 유저 정보 가져오기
			User loginedUserView = pService.getUserInfoLogin(userParam);
			// userView MODEL 전달
			req.setAttribute("loginedUserView", loginedUserView);
			System.out.println("로그인 유저 : " + loginedUserView);

			// timetable, location 리스트 받기
			List<Timetable> ttbList = ttbService.getTimetableList(planView);
			List<Location> locList = ttbService.getLocationList(planView, ttbList);

			// timetable 과 location이 1:1 대응하지 않는 경우 (DB데이터 문제)
			if (ttbList.size() != locList.size()) {
				System.out.println("[ERR] 타임테이블과 위치정보의 개수가 일치하지 않습니다.");
				return;
			}

			// JSON 형태로 변환
			String ttbListStr = gson.toJson(ttbList);
			String locListStr = gson.toJson(locList);

			// 파라미터 지정
			req.setAttribute("ttbList", ttbListStr);
			req.setAttribute("locList", locListStr);

			// 가계부 정보 가져오기
			Account accView = pService.getAccount(planView);
			// accView MODEL 전달
			req.setAttribute("accView", accView);

			//plan_idx 세션에 추가 
			//req.getSession().setAttribute("plan_idx", plan_idx);
		}
		
		// 뷰 지정
		req.getRequestDispatcher("/plan/write.jsp")
		.forward(req, resp);
	}
}
