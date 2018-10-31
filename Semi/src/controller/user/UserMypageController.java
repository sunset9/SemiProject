package controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.plan.Plan;
import dto.user.Bookmark;
import dto.user.User;
import service.user.UserService;
import service.user.UserServiceImpl;

/**
 * Servlet implementation class UserUpdateController
 */
@WebServlet("/user/myPage")
public class UserMypageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//현재 유저 정보 가져오기
		User cUser = (User) req.getSession().getAttribute("user");
		//System.out.println("마이페이지 현재 세션에 있는 유저 : "+cUser);
		User cUserSocial = (User) req.getSession().getAttribute("socialUser");
		//System.out.println("마이페이지 현재 세션에 있는 소셜 유저 : "+cUserSocial);
		
		if(cUserSocial == null) {
			System.out.println("아이디 로그인 유저");
			
			//현재 유저의 일정들 가져오기
			List<Plan> plannerList = userService.getPlanner(cUser);
			//planList
			req.setAttribute("plannerList", plannerList);
			System.out.println("유저마이페이지컨트롤러에서 plannerList : "+plannerList);
			
			//현재 유저의 포스팅 개수 가져오기 
			int cntPlan = userService.getCntPlan(cUser);
			req.setAttribute("cntPlan", cntPlan);
			
			//현재 유저의 총 여행 거리 가져오기 
			int totDist = userService.getTotDist(cUser);
			req.setAttribute("totDist", totDist);
			
			//모든 일정 가져오기
			List<Plan> allPlanList = userService.getAllPlanList(cUser);
			//System.out.println("userMyPageController allPlanList : "+allPlanList);
			req.setAttribute("allPlanList", allPlanList);
			
			//현재 유저의 북마크 가져오기
			List<Bookmark> bookMarkList = userService.getBookmarkList(cUser);
			//bookMarkList
			req.setAttribute("bookMarkList", bookMarkList);
			//System.out.println("mypagecontroller bList 잘 가져왔나 : "+bookMarkList); --> OK
			
		} else if(cUser == null) {
			System.out.println("소셜 로그인 유저");
			
			//현재 유저의 일정들 가져오기
			List<Plan> plannerList = userService.getPlanner(cUserSocial);
			//planList
			req.setAttribute("plannerList", plannerList);
			
			//현재 유저의 포스팅 개수 가져오기 
			int cntPlan = userService.getCntPlan(cUserSocial);
			req.setAttribute("cntPlan", cntPlan);
			
			//현재 유저의 총 여행 거리 가져오기 
			int totDist = userService.getTotDist(cUserSocial);
			req.setAttribute("totDist", totDist);
			
			//모든 일정 가져오기
			List<Plan> allPlanList = userService.getAllPlanList(cUserSocial);
			//System.out.println("userMyPageController allPlanList : "+allPlanList);
			req.setAttribute("allPlanList", allPlanList);

			
			//현재 유저의 북마크 가져오기
			List<Bookmark> bookMarkList = userService.getBookmarkList(cUserSocial);
			//bookMarkList
			req.setAttribute("bookMarkList", bookMarkList);
			//System.out.println("mypagecontroller bList 잘 가져왔나 : "+bookMarkList); --> OK
		}
		
		//정보수정페이지(마이페이지)로 이동
		req.getRequestDispatcher("/user/myPage.jsp").forward(req, resp);
	}
	
	
}
