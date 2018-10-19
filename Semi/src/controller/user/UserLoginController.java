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


@WebServlet("/user/login")
public class UserLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.getRequestDispatcher("/user/login.jsp").forward(req, resp);
		}
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//파라미터 처리
			User param = userService.getParam(req,resp);

			System.out.println(param);

			
			//로그인 처리
			//존재하는 회원인지 확인 후 
			//존재하는 회원이면 true반환
			boolean login = userService.login(param);
			System.out.println("login :"  +login);
			//유저 정보 얻어오기
			User user = userService.getUserByEmail(param);
			System.out.println("user:" +user);
			//세션 정보 저장하기
			//유저 객체로 넘기기
			req.getSession().setAttribute("user_idx", user.getUser_idx());
			System.out.println("user_idx :"+ user.getUser_idx());
			req.getSession().setAttribute("login", login);
			req.getSession().setAttribute("user", user);
			
			resp.sendRedirect("/main");
		}
	
}
