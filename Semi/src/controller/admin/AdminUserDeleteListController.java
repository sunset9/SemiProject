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

@WebServlet("/admin/user/deleteList")
public class AdminUserDeleteListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private User user = new User();
	private AdminUserService adminUserService= new AdminUserServiceImpl();
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String names = req.getParameter("names");
		
		if(!"".equals(names) && names != null) {
			adminUserService.userListDelete(names);
			
		}
		resp.sendRedirect("/admin/user/main");
		
		
		}
}
