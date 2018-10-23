package controller.user;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.user.User;
import service.user.UserService;
import service.user.UserServiceImpl;

/**
 * Servlet implementation class SocialUserUpdateController
 */
@WebServlet("/socialUser/update")
public class SocialUserUpdateController extends HttpServlet {
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
		//한글 인코딩
		req.setCharacterEncoding("UTF-8");
		
		//요청 파라미터 처리 
		Map<String, String> param = userService.getParamUpdate(req, resp);
		// System.out.println("socialUpdateController : "+param);

		User cUser = (User) req.getSession().getAttribute("socialUser"); // 현재 유저
		System.out.println("현재 유저의 닉네임 : " + cUser.getNickname());
		System.out.println("현재 유저의 비밀번호 : " + cUser.getPassword());

		// 닉네임 변경
		if (!(param.get("nickname").equals(cUser.getNickname()))) {
			userService.changeNick(param);

			// db에서 유저 정보 가져오기
			User changedUser = userService.getUserByid(cUser);
			System.out.println("닉네임 변경됨 : " + changedUser);
		}

		// 마이페이지로 이동
		resp.sendRedirect("/user/myPage");
		}
}
