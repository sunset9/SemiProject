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
 * Servlet implementation class UserCheckController
 */
@WebServlet("/user/check")
public class UserCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserService uService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String inputUserid = req.getParameter("inputUserid");
		// System.out.println("UserCheckController : "+inputUserid); -> OK

		User checkUser = new User();
		checkUser.setId(inputUserid);

		// 아이디 중복확인
		// true면 존재하지 않는다 -> 사용가능한 id
		// false면 존재한다 -> 사용불가능한 id
		boolean checkid = uService.checkid(checkUser);

		if (checkid == true) {
			resp.getWriter().write('1');
		} else {
			resp.getWriter().write('0');
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String inputUserId = req.getParameter("inputUserId");
		String inputUserPw = req.getParameter("inputUserPw");

		User inputUser = new User();
		inputUser.setId(inputUserId);
		inputUser.setPassword(inputUserPw);
		
		//id가 존재하지 않음 => 존재하지 않는 회원입니다. -> 5
		//id 존재하는데 비밀번호는 틀림 => 비밀번호가 틀렸습니다. -> 6
		
		boolean checkId = uService.checkid(inputUser);
		
		boolean checkPw = uService.checkPw(inputUser); //true는 비번맞음, false면 비번틀림
		
		boolean checkStatus = uService.checkStatus(inputUser);
		
		if(checkId == true) {
			//아이디 존재하지 않음, 로그인 실패
			resp.getWriter().write('1');
		} else if (checkId == false && checkPw == false){
			//아이디는 존재하지만 비밀번호가 틀릴때
			resp.getWriter().write('2');
		} else if (checkId == false && checkPw == true && checkStatus == true) {
			//아이디가 존재하며 비밀번호도 맞을때 -> 로그인 성공!
			resp.getWriter().write('3');
		} else if (checkId == false && checkPw == true && checkStatus == false) {
			//아이디가 존재하고, 비밀번호도 맞는데 status는 0일때 -> 존재하지 않는 회원
			resp.getWriter().write('4');
		}
		
		
		
	}
}
