package controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.user.User;
import service.plan.UserService;

@WebServlet("/MyPageUpdateController")
public class MyPageUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserService uService = new UserService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 세션에서 유저 정보 가져오기
		User userinfo = uService.getUserInfoSess(req);
		
		// 마이페이지 수정폼 띄우기
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 입력 폼에서 정보 받아옴
		User param = uService.getUpdateParam(req);
		
		// 회원정보 수정
		uService.updateInfo(param);
	}
}
