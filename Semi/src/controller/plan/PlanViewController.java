package controller.plan;

import java.io.IOException;
import java.util.EnumMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dto.Account.AccType;
import dto.plan.Plan;
import dto.timetable.Location;
import dto.timetable.Timetable;
import dto.user.Bookmark;
import dto.user.User;
import service.bookmark.BookmarkService;
import service.bookmark.BookmarkServiceImpl;
import service.plan.PlanService;
import service.plan.PlanServiceImpl;
import service.stroy.StoryService;
import service.stroy.StoryServiceImpl;
import service.timetable.TimetableService;
import service.timetable.TimetableServiceImpl;
import service.user.UserService;
import service.user.UserServiceImpl;

@WebServlet("/plan")
public class PlanViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	UserService uService = new UserServiceImpl();
	PlanService pService = new PlanServiceImpl();
	TimetableService ttbService = new TimetableServiceImpl();
	StoryService sService = new StoryServiceImpl();
	BookmarkService bService = new BookmarkServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println();
		System.out.println("----- PlanViewController -----");
		GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm");
        Gson gson = gsonBuilder.create();

        Plan planParam = pService.getParam(req);
        
		// 일정 기본 정보 가져오기
		Plan planView = pService.getPlanInfo(planParam);
		// 일정 마지막날, 시간순서로 빠른 2곳 나라이름 띄워주기
		String [] cName = pService.getCountryName(planView);
		req.setAttribute("cName1", cName[0]);
		req.setAttribute("cName2", cName[1]);
		//planView MODEL 전달
		req.setAttribute("planView", planView);
		System.out.println(planView);
		// 게시자 유저 정보 가져오기
		User writtenUserView = pService.getUserInfo(planView);
		// 총 게시물 수, 총 여행거리 정보 추가된  유저정보 가져오기
		writtenUserView = uService.getUseraddedInfo(writtenUserView);
		//userView MODEL 전달
		req.setAttribute("writtenUserView", writtenUserView);
		
//		---------------------로그인 유저 파라미터 가져오기
		// 요청파라미터(user_idx) -> Plan 모델
		User userParam = pService.getSessionUser(req);
		// 로그인 유저 정보 가져오기
		User loginedUserView = pService.getUserInfoLogin(userParam);
		//userView MODEL 전달
		req.setAttribute("loginedUserView", loginedUserView);
				
//		--------------------------------------------
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
		
		Bookmark book = bService.getBookmarkInfo(planParam);
		
		req.setAttribute("bookmark", book);
//		--------------------------------------------
		
		// 가계부 정보 가져오기
		EnumMap<AccType, Long> accEnumMap = new EnumMap<AccType, Long>(AccType.class);
	
		// 카테고리 별
		accEnumMap.put(AccType.airfare, pService.getAccountAccTpeCost(planParam, AccType.airfare));
		accEnumMap.put(AccType.traffic, pService.getAccountAccTpeCost(planParam, AccType.traffic));
		accEnumMap.put(AccType.stay, pService.getAccountAccTpeCost(planParam, AccType.stay));
		accEnumMap.put(AccType.admission, pService.getAccountAccTpeCost(planParam, AccType.admission));
		accEnumMap.put(AccType.food, pService.getAccountAccTpeCost(planParam, AccType.food));
		accEnumMap.put(AccType.play, pService.getAccountAccTpeCost(planParam, AccType.play));
		accEnumMap.put(AccType.shop, pService.getAccountAccTpeCost(planParam, AccType.shop));
		accEnumMap.put(AccType.etc, pService.getAccountAccTpeCost(planParam, AccType.etc));
		
		req.setAttribute("accTypeCost", new Gson().toJson(accEnumMap));
		
		// 총합
		Long acc_total = pService.getAccountTotal(accEnumMap);
		req.setAttribute("acc_total", acc_total);
		
		// view 폼 띄우기
		req.getRequestDispatcher("/plan/view.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
