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
			req.getRequestDispatcher("/view/user/login.jsp").forward(req, resp);
		}
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//파라미터 처리
			User param = userService.getParam(req,resp);
			//System.out.println(param);
			
			//로그인 처리
			boolean login = userService.login(param);
			
			//유저 정보 얻어오기
			User user = userService.getUserByEmail(param);
			
			//세션 정보 저장하기
			//유저 객체로 넘기기
			req.getSession().setAttribute("login", login);
			req.getSession().setAttribute("email", user.getEmail());
			req.getSession().setAttribute("nickname", user.getNickname());
			req.getSession().setAttribute("profile", user.getProfile());
			req.getSession().setAttribute("grade", user.getGrade());
			req.getSession().setAttribute("sns_type", user.getSns_type());
			req.getSession().setAttribute("create_date", user.getCreate_date());
			
			resp.sendRedirect("/main");
		}
	
}
