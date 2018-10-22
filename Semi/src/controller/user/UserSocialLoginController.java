package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.user.User;
import service.user.UserService;
import service.user.UserServiceImpl;

/**
 * Servlet implementation class UserSocialLoginController
 */
@WebServlet("/user/socialLogin")
public class UserSocialLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//한글 인코딩
			req.setCharacterEncoding("UTF-8");
		
			//파라미터 처리
			User param = userService.getParamSocial(req, resp);
			System.out.println("social : "+param);
			
			//로그인 처리
			boolean login = userService.socialLogin(param);
						
			//유저 정보 얻어오기 
			User socialUser = userService.getUserByid(param);
			
			//세션 정보 저장
			req.getSession().setAttribute("user_idx", socialUser.getUser_idx());
			req.getSession().setAttribute("login", login);
			req.getSession().setAttribute("socialUser", socialUser);
			
			req.getSession().setMaxInactiveInterval(18000);
			
			resp.sendRedirect("/main");
		}
	
}
