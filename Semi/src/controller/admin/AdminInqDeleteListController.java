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


@WebServlet("/admin/inquiry/deleteList")
public class AdminInqDeleteListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminInquiryService adminInquiryService = new AdminInquiryServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String names = req.getParameter("names");
		
		System.out.println("실행쓰?"+names);
		
		if(!"".equals(names) && names != null) {
			adminInquiryService.inqListDelete(names);
		}
		
		resp.sendRedirect("/admin/inquiry/list");
	}
}
