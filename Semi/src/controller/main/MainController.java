package controller.main;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.plan.Plan;
import dto.user.User;
import service.main.MainService;
import service.main.MainServiceImpl;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MainService mainService = new MainServiceImpl();
	User cUser = null;
	User cUserSocial = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 추천콘텐츠 리스트 가져오기
		List recomPlanList = mainService.getRecommendedContents();

		// 최신콘텐츠 리스트 가져오기
		List newestPlanList = mainService.getNewestContents();

		// 리스트 정보 저장하기
		req.setAttribute("recomPlanList", recomPlanList);
		req.setAttribute("newestPlanList", newestPlanList);

		// 뷰 지정해주기
		req.getRequestDispatcher("/main/main.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 한글 인코딩
		req.setCharacterEncoding("UTF-8");

		// 새 일정 파라미터 받아오기(제목, 출발일, 도착일, 여행전후)
		Plan param = mainService.getNewPlanParam(req, resp);
//		System.out.println("메인컨트롤러 : "+param);
		
		// user_idx 구하기
		cUser = (User) req.getSession().getAttribute("user");
		cUserSocial = (User) req.getSession().getAttribute("socialUser");

		if (cUserSocial == null) {
			System.out.println("아이디 로그인 유저");
			// 새 일정 등록하기
			mainService.createPlan(param, cUser);
			
			// insert한 plan의 plan_idx 가져오기
			int plan_idx = mainService.getPlan_idx();
			System.out.println("메인 컨트롤러 plan_idx : "+plan_idx);
			

 			//plan_idx 세션에 추가
			req.getSession().setAttribute("plan_idx", plan_idx);
			
		} else if (cUser == null) {
			System.out.println("소셜 로그인 유저");
			//새 일정 등록 
			mainService.createPlan(param, cUserSocial);
			
			// insert한 plan의 plan_idx 가져오기
			int plan_idx = mainService.getPlan_idx();
			System.out.println("plan_idx : " + plan_idx);
			

			//plan_idx 세션에 추가 
			req.getSession().setAttribute("plan_idx", plan_idx);
		}
		
		resp.sendRedirect("/plan/write");
	}

}
