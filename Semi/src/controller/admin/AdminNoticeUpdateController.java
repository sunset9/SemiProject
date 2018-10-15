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




@WebServlet("admin/notice/update")
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
		
	
		
		//--- MultipartRequest �������� �Ű� ���� �غ�---
		// 1. ��û ��ü 
		//	���� ���� �ʿ� ���� 
		
		// 2. ���� ���� ��ġ
		// String ���� ������ ���� ��� ���� 
		String saveDirectory = getServletContext().getRealPath("cos/upload");
		
//		System.out.println( saveDirectory);
		
		// 3. ���ε� ���� ������ 
		int maxPostSize = 1 *1024*1024; // 10MB ���� 
		
		// 4. ���ڵ� 
		// ���ε� ���� ���ڵ� ��� 
		String encoding = "UTF-8";
		
		
		// 5. �ߺ� ���� �̸� ��å
		// DefaultFileRenamePolicy �� �ߺ������� ������ 
		// ���� �̸� �ڿ� ���ڸ� �߰��ϰ� 1���� ������Ų��. 
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		
		//-----------------------------------------------
		
		// MultipartRequest ��ü ���� 
		// ���� ���ε� ó�� 
		MultipartRequest mul = new MultipartRequest(req, saveDirectory, maxPostSize, encoding, policy);

		notice.setTitle(mul.getParameter("title"));
		
		notice.setContent(mul.getParameter("content"));
		
		file.setStored_name(mul.getFilesystemName("file"));
		System.out.println(mul.getFilesystemName("file"));
		file.setOrigin_name(mul.getOriginalFileName("file"));
		
		adminNoticeService.update(notice);
		
		
		adminNoticeService.deleteNoticeFile(file);

		resp.sendRedirect("/notice/list");
	
	}
}
