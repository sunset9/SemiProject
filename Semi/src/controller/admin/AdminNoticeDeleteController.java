package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.board.NoticeDao;
import dao.board.NoticeDaoImpl;
import dao.board.NoticeFileDao;
import dao.board.NoticeFileDaoImpl;
import dto.board.Notice;
import dto.board.NoticeFile;
import service.board.AdminInquiryService;
import service.board.AdminInquiryServiceImpl;
import service.board.AdminNoticeService;
import service.board.AdminNoticeServiceImpl;
import service.board.NoticeService;
import service.board.NoticeServiceImpl;


@WebServlet("admin/notice/delete")
public class AdminNoticeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminNoticeService adminNoticeService = new AdminNoticeServiceImpl();
	private Notice notice = new Notice();
	private NoticeFile file = new NoticeFile();
   

	@Override
	protected void doGet(HttpServletRequest res, HttpServletResponse resp) throws ServletException, IOException {
		
		// �Ķ���� ���
		notice = adminNoticeService.getParam(res, resp);
		
		// �Խù� ����� 
		adminNoticeService.delete(notice);
		
		// �Խù��� ���õ� ���� ã��
		file.setNotice_idx(notice.getNotice_idx());
		
		// ���� �����
		adminNoticeService.deleteNoticeFile(file);
		
		resp.sendRedirect("");
	}
}
