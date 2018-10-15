package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Notice;
import service.board.AdminNoticeService;
import service.board.AdminNoticeServiceImpl;
import service.board.NoticeService;
import service.board.NoticeServiceImpl;


@WebServlet("admin/notice/view")
public class AdminNoticeViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminNoticeService adminNoticeService = new AdminNoticeServiceImpl();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		// �Ķ���� �޾ƿ��� 
		Notice notice = adminNoticeService.getParam(req, resp);

		// �Խù� �󼼺���
		notice = adminNoticeService.view(notice);

		// ���� �𵨿� ��� ����
		req.setAttribute("notice", notice);
		
		// ������ ȭ�� ����
		req.getRequestDispatcher("").forward(req, resp);
	}
}
