package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.InqFile;
import dto.board.Inquiry;
import dto.board.Reply;
import service.admin.AdminService;
import service.admin.AdminServiceImpl;
import service.board.AdminInquiryService;
import service.board.AdminInquiryServiceImpl;


@WebServlet("/admin/inquiry/delete")
public class AdminInqDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminInquiryService adminInquiryService = new AdminInquiryServiceImpl();
	private Inquiry inquiry = new Inquiry();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 확인
		boolean check = adminInquiryService.loginCheck(req);
				
		if(!check) {
			resp.sendRedirect("/user/login");
			return;
		}
		
		// 파라미터 값 받아오기
		inquiry = adminInquiryService.getParam(req, resp);
		
		// 게시물 삭제 하기
		adminInquiryService.delete(inquiry);
		
		resp.sendRedirect("/admin/inquiry/list");
	}
   

}
