package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import dao.board.NoticeFileDao;
import dao.board.NoticeFileDaoImpl;
import dto.board.Notice;
import dto.board.NoticeFile;
import service.board.AdminNoticeService;
import service.board.AdminNoticeServiceImpl;
import service.board.NoticeService;
import service.board.NoticeServiceImpl;




@WebServlet("/admin/notice/update")
public class AdminNoticeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	private AdminNoticeService adminNoticeService = new AdminNoticeServiceImpl();
	private Notice notice = new Notice();
	private NoticeFile file = new NoticeFile();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		//�Ķ���� ���
		notice = adminNoticeService.getParam(req, resp);

		// �Խù� �ҷ����� 
		notice = adminNoticeService.view(notice);

		// ���� �𵨷� �����ϱ�
		req.setAttribute("notice", notice);		
		
		// ������ ȭ�� ����
		req.getRequestDispatcher("").forward(req, resp);
		
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
	
		
//		adminNoticeService.update(notice);
		
		
//		adminNoticeService.deleteNoticeFile(file);

		resp.sendRedirect("/notice/list");
	
	}
}
