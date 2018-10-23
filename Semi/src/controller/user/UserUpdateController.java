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
		System.out.println("현재 유저의 비밀번호 : "+cUser.getPassword());
		System.out.println("요청 파라미터로 넘어온 nickname : "+param.get("nickname"));
		System.out.println("요청 파라미터로 넘어온 newPw : "+param.get("newPw"));
		
		//현재 유저의 닉네임이랑 요청 파라미터로 넘어온 닉네임이 다르다. 가 트루일때
		
		//닉네임 변경 
		if(!(param.get("nickname").equals(cUser.getNickname()))) {
			userService.changeNick(param);
			
			//db에서 유저 정보 가져오기
			User changedUser = userService.getUserByid(cUser);
			System.out.println("닉네임 변경됨 : "+changedUser);
		}
		
		if(!(param.get("currPw").isEmpty() || param.get("newPw").isEmpty() || param.get("newPwCheck").isEmpty())) {
			//회원정보수정폼에서 '현재 비밀번호', '새 비밀번호', '비밀번호 확인' 란이 모두 채워져있어야 수행됨
			//System.out.println("다 채워져있다!!"); -> OK!!
			User cUser2 = (User)req.getSession().getAttribute("user"); //현재 유저
			//param의 currPw를 현재 세션에 저장된 유저의 비밀번호와 비교해서 같으면 비번 변경 가능
			if( param.get("currPw").equals(cUser2.getPassword()) ) {
				userService.changePw(param);
				
				//db에 저장된 유저 정보 가져오기
				User changedUser = userService.getUserByid(cUser);
				System.out.println("비밀번호 변경됨. : "+changedUser);
			}
		}
		
		//마이페이지로 이동
		resp.sendRedirect("/user/myPage");
	}
}
