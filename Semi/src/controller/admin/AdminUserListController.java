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

@WebServlet("/admin/user/list")
public class AdminUserListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminUserService adminUserService = new AdminUserServiceImpl();
	private User user = new User();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//전체 회원수 조회 
		int userCnt = adminUserService.userCnt();
		
		// 여행자등급의 사용자 수 조회 
		int touristCnt = adminUserService.touristCnt();
		
		// 여행작가등급의 사용자 수 조회
		int authorCnt = adminUserService.authorCnt();
		
		// 관리자 등급의 사용자 수 조회
		int managerCnt = adminUserService.managerCnt();
		
		
		// 조회한 정보 요청에 담아 보내기 
		req.setAttribute("userCnt", userCnt);
		req.setAttribute("touristCnt", touristCnt);
		req.setAttribute("authorCnt", authorCnt);
		req.setAttribute("manageCnt", managerCnt);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		user = adminUserService.getParam(req, resp);
		
		
		// 이메일로 검색
		adminUserService.selectUserBy(user);
	
	}
}
