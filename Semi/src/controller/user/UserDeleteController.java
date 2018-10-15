package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.user.User;
import service.user.UserService;
import service.user.UserServiceImpl;


@WebServlet("/user/delete")
public class UserDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private User user = new User();
	private UserService userService= new UserServiceImpl();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//뷰 지정	
		req.getRequestDispatcher("/view/user/delete.jsp").forward(req, resp);
		}
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			User user = req.getSession().getAttribute();
			//System.out.println(user);
		
			userService.deleteUserByEmail(user);
			
			req.getSession().invalidate();
			
			resp.sendRedirect("/main");
		
		}
}
