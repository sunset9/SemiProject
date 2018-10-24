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
		System.out.println("MyPage 현재 유저 : "+cUser);
		
		//현재 유저의 일정들 가져오기
		List<Plan> plannerList = userService.getPlanner(cUser);
		//planList
		req.setAttribute("plannerList", plannerList);
		
		
		//현재 유저의 북마크 가져오기
		List<Bookmark> bookMarkList = userService.getBookmarkList(cUser);
		//bookMarkList
		req.setAttribute("bookMarkList", bookMarkList);
		//System.out.println("mypagecontroller bList 잘 가져왔나 : "+bookMarkList); --> OK
		
		//정보수정페이지(마이페이지)로 이동
		req.getRequestDispatcher("/user/myPage.jsp").forward(req, resp);
	}
	
	
}
