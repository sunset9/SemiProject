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
import dto.bookmark.Bookmark;
import dto.plan.Plan;
import dto.timetable.Location;
import dto.timetable.Timetable;
import dto.user.User;
import service.bookmark.BookmarkService;
import service.bookmark.BookmarkServiceImpl;
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
	BookmarkService bService = new BookmarkServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println();
		System.out.println("----- PlanViewController -----");
		GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm");
        Gson gson = gsonBuilder.create();
        
        Plan planParam = new Plan();
        System.out.println(req.getParameter("plan_idx"));
        // view로 들어오는 파라미터값 확인
        if( req.getParameter("plan_idx") != null && !"".equals(req.getParameter("plan_idx"))) {
//        	req.getSession().setAttribute("plan_idx", planParam.getPlan_idx());
        	planParam.setPlan_idx(Integer.parseInt(req.getParameter("plan_idx")));
        } else {
        	planParam = pService.getSessionPlan(req);
        }
        
//		---------------------플래너 파라미터 가져오기
		// 요청파라미터(plan_idx) -> Plan 모델 
		System.out.println("Session 값 : " + planParam);
		
		// 일정 기본 정보 가져오기
		Plan planView = pService.getPlanInfo(planParam);
		//planView MODEL 전달
		req.setAttribute("planView", planView);
		System.out.println(planView);
		// 게시자 유저 정보 가져오기
		User writtenUserView = pService.getUserInfo(planView);
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
		
		// 스토리 리스트 가져오기
		//List<Story> storyList = sService.getStoryList(plan);

		// 스토리 댓글 리스트 가져오기
		//List<Comment> commList = sService.getCommentList(storyList);
		
		Bookmark book = bService.getBookmarkInfo(planParam);
		
		req.setAttribute("bookmark", book);
//		--------------------------------------------
		
		// 가계부 정보 가져오기
		Account accView = pService.getAccount(planParam);
		
		int airfare = pService.getAccountAirfareCost(planParam);
		int traffic = pService.getAccountTrafficCost(planParam);
		int stay = pService.getAccountStayCost(planParam);
		int admission = pService.getAccountAdmissionCost(planParam);
		int food = pService.getAccountFoodCost(planParam);
		int play = pService.getAccountPlayCost(planParam);
		int shop = pService.getAccountShopCost(planParam);
		int etc = pService.getAccountEtcCost(planParam);
		
		req.setAttribute("airfare", airfare);
		req.setAttribute("traffic", traffic);
		req.setAttribute("stay", stay);
		req.setAttribute("admission", admission);
		req.setAttribute("food", food);
		req.setAttribute("play", play);
		req.setAttribute("shop", shop);
		req.setAttribute("etc", etc);
		
		int acc_total = airfare+traffic+stay+admission+food+play+shop+etc;
		req.setAttribute("acc_total", acc_total);
		
		System.out.println(accView);
		//accView MODEL 전달
		req.setAttribute("accView", accView);
	    
		// view 폼 띄우기
		req.getRequestDispatcher("/plan/view.jsp").forward(req, resp);
	}
}
