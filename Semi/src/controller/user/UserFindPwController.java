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


@WebServlet("/user/findPw")
public class UserFindPwController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    UserService uService = new UserServiceImpl();
    
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/user/findPw.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파라미터 잘 왔는지 확인
		// System.out.println("UserFindPwController "+req.getParameter("email"));
		
		User user = new User();
		
		// findPw.jsp 의 요청 파라미터 처리 
		String email = req.getParameter("email");
		user.setId(email);
		
		// 회원 db에 존재하는 email인지 조회 
		// true : 존재하지 않음
		// false : 존재함
		boolean checkEmail = uService.checkid(user);
		//System.out.println(checkEmail);
		
		if(checkEmail == false) {
			// 임시 비밀번호를 발급
			String tempPw = uService.createTempPw(10);
			System.out.println(tempPw);
			
			// 비밀번호 변경
			uService.changeTempPw(email, tempPw);
			
			// 입력받은 이메일로 임시 비밀번호 전송
			uService.gmailSend(email, tempPw);
			
			resp.sendRedirect("/user/login");
		} else {
			resp.sendRedirect("/user/findPwFailed");
		}
		
	}

}
