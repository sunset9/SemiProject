package controller.mypage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.plan.Plan;
import dto.plan.User;
import service.plan.PlanService;

@WebServlet("/MyPageController")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PlanService pService = new PlanService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 세션에서 유저 정보 가져오기
		Plan plan = new Plan();
		User userinfo = pService.getUserInfo(plan);
		
		// 일정 목록 가져오기
		List<Plan> planList = pService.getPlanList(userinfo);
		
		// 북마크 목록 가져오기
		List<Plan> bookmarkList = pService.getBookmarkList(userinfo);
		
		// 마이페이지 폼 띄우기
	}
}
