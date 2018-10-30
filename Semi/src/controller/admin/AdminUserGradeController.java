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


@WebServlet("/admin/user/grade")
public class AdminUserGradeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminUserService adminUserService= new AdminUserServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user= new User();
		
		// 파라미터 얻어오기
		String user_idx = req.getParameter("user_idx");
		String grade = req.getParameter("usergrade");
		
		//유저에 유저 번호 설정 
		user.setUser_idx(Integer.parseInt(user_idx));
		user.setGrade(grade);
		
		// 등급 올리기 수행 
		boolean success = adminUserService.upgrade(user);
		
		// view 지정 
		resp.getWriter().append("{\"success\":"+success+"}");
		
	}

}
