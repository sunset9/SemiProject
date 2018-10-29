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
			//한글 인코딩
			req.setCharacterEncoding("UTF-8");
		
			//파라미터 처리
			User param = userService.getParam(req,resp);

			System.out.println("loginController param : "+param);

			
			//로그인 처리
			//존재하는 회원인지 확인 후 
			//존재하는 회원이면 true반환
			boolean login = userService.login(param);
			System.out.println("login :"  +login);
			
			//유저 정보 얻어오기
			User user = userService.getUserByid(param);
			System.out.println("loginController user : "+user);
			
			HttpSession session = req.getSession();

			//세션 정보 저장하기
			//유저 객체로 넘기기
			session.setAttribute("user_idx", user.getUser_idx());
			session.setAttribute("login", login);
			session.setAttribute("user", user);
		
			
			
			resp.setHeader("Cache-Controll", "no-cache");
			
			String grade = user.getGrade();
			
//			System.out.println("grade :"+grade);
			if(grade.equals("관리자")) {
				resp.sendRedirect("/admin/main");
			} else {
				resp.sendRedirect("/main");
				}
			}
	
}
