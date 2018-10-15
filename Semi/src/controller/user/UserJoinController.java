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
 * Servlet implementation class UserJoinController
 */
@WebServlet("/user/join")
public class UserJoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//뷰 지정
			req.getRequestDispatcher("/view/user/join.jsp").forward(req, resp);
		}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//요청 파라미터 처리
		User param = userService.getParam(req, resp);
		//System.out.println(param);
		
		//중복확인
		boolean checkEmail = userService.checkEmail(param);
		
		if(checkEmail) {
			//회원가입 
			userService.join(param);
		}
		
		//메인으로 이동
		resp.sendRedirect("/main");
	}
}
