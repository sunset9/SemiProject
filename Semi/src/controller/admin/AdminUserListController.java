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
		adminUserService.selectUserAll();
		
		req.getRequestDispatcher("").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		user = adminUserService.getParam(req, resp);
		
		
		// 이메일로 검색
		adminUserService.selectUserBy(user);
	
	}
}
