package controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.user.User;
import service.user.AdminUserService;
import service.user.AdminUserServiceImpl;

@WebServlet("/admin/user/main")
public class AdminUserMainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminUserService adminUserService = new AdminUserServiceImpl();
	private User user = new User();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 확인
		boolean check = adminUserService.loginCheck(req);
				
		if(!check) {
			resp.sendRedirect("/user/login");
			return;
		}
		
		String grade=null;
		
		//전체 회원수 조회 
		int userCnt = adminUserService.userCnt(grade);
		
		grade = "여행자";

		// 여행자등급의 사용자 수 조회 
		int touristCnt = adminUserService.userCnt(grade);
		
		grade = "여행작가";
		
		// 여행작가등급의 사용자 수 조회
		int authorCnt = adminUserService.userCnt(grade);
		
		grade = "관리자";
		
		// 관리자 등급의 사용자 수 조회
		int managerCnt = adminUserService.userCnt(grade);
		
		
		// 조회한 정보 요청에 담아 보내기 
		req.setAttribute("userCnt", userCnt);
		req.setAttribute("touristCnt", touristCnt);
		req.setAttribute("authorCnt", authorCnt);
		req.setAttribute("managerCnt", managerCnt);
		
		req.getRequestDispatcher("/admin/user/userMain.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		user = adminUserService.getParam(req, resp);
		
		
		// 이메일로 검색
		adminUserService.selectUserBy(user);
	
	}
}
