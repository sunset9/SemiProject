package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.board.AdminNoticeService;
import service.board.AdminNoticeServiceImpl;


@WebServlet("/admin/notice/deleteList")
public class AdminNotiDeleteList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminNoticeService adminNoticeService = new AdminNoticeServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String names = req.getParameter("names");
		
		if(!"".equals(names) && names != null) {
			adminNoticeService.noticeListDelete(names);
			
		}
		
		resp.sendRedirect("/admin/notice/list");
	
	}
}
