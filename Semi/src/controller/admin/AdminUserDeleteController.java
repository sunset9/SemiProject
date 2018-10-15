package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.user.User;
import service.user.AdminUserService;
import service.user.AdminUserServiceImpl;
import service.user.UserService;
import service.user.UserServiceImpl;

@WebServlet("/admin/user/delete")
public class AdminUserDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private User user = new User();
	private AdminUserService adminUserService= new AdminUserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//뷰 지정	
		req.getRequestDispatcher("").forward(req, resp);
		}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		user = adminUserService.getParam(req, resp);
		
		adminUserService.deleteUser(user);
		
		
		
		}
}
