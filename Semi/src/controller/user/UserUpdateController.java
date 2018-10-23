package controller.user;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.user.User;
import service.user.UserService;
import service.user.UserServiceImpl;

/**
 * Servlet implementation class UserUpdateController
 */
@WebServlet("/user/update")
public class UserUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println(req.getSession().getAttribute("user"));
		//System.out.println(req.getSession().getAttribute("login"));
		//System.out.println(req.getSession().getAttribute("user_idx"));
		
		req.getRequestDispatcher("/user/update.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//요청 파라미터 처리
		Map<String, String> param = userService.getParamUpdate(req, resp);
		System.out.println("updateController : "+param);
		

		User cUser = (User)req.getSession().getAttribute("user"); //현재 유저
		System.out.println("현재 유저의 닉네임 : "+cUser.getNickname());
		System.out.println("요청 파라미터로 넘어온 닉네임 : "+param.get("nickname"));
		
		//System.out.println(!(cUser.getNickname() == param.get("nickname")));
		
		//닉네임 변경 
		if(!(cUser.getNickname() == param.get("nickname"))) {
			userService.changeNick(param);
			
			//db에서 유저 정보 가져오기
			User changedUser = userService.getUserByid(cUser);
			System.out.println("닉네임 변경된 유저 : "+changedUser);
			
			HttpSession session = req.getSession();
			session.setAttribute("user", changedUser);
			session.setMaxInactiveInterval(18000);
		}
		
		if (cUser.getPassword() == param.get("currPw")) {
			if (param.get("newPw") == param.get("newPwCheck")) {
				userService.changePw(param);

				// db에서 유저 정보 가져오기
				User changedUser = userService.getUserByid(cUser);
				System.out.println("비번 변경된 유저 : " + changedUser);

				HttpSession session = req.getSession();
				session.setAttribute("user", changedUser);
				session.setMaxInactiveInterval(18000);
			}
		}		
		
		
		//마이페이지로 이동
		resp.sendRedirect("/user/myPage");
	}
}
