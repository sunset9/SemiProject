package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.user.User;
import service.user.UserService;
import service.user.UserServiceImpl;


@WebServlet("/user/delete")
public class UserDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private User user = new User();
	private UserService userService= new UserServiceImpl();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//현재 로그인한 유저 정보 가져오기
		User user = (User) req.getSession().getAttribute("user");
		User socialUser = (User) req.getSession().getAttribute("socialUser");
		//System.out.println("deleteController : "+user);
		//System.out.println("deleteController : "+socialUser);
		
		//유저 삭제 
//		System.out.println(user != null);
//		System.out.println(socialUser == null);
		if(user!=null) {
			userService.deleteUserByid(user);
		}
		if(socialUser!=null) {
			userService.deleteUserByid(socialUser);
		}
		
		//세션 삭제
		req.getSession().invalidate();
		
		//뷰 지정	
		req.getRequestDispatcher("/main").forward(req, resp);
		
		
		}
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			//userService.deleteUserByid(user);
			
			//req.getSession().invalidate();
			
			//resp.sendRedirect("/main");
		
		}
}
