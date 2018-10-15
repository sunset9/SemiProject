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

import dao.board.QnaDao;
import dao.board.QnaDaoImpl;
import dao.board.QnaFileDao;
import dao.board.QnaFileDaoImpl;
import dto.board.Qna;
import dto.board.QnaFile;
import service.board.AdminQnaService;
import service.board.AdminQnaServiceImpl;
import service.board.QnaService;
import service.board.QnaServiceImpl;


@WebServlet("admin/qna/update")
public class AdminQnaUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private AdminQnaService adminQnaService = new AdminQnaServiceImpl();
	private Qna qna = new Qna();
	private QnaFile file = new QnaFile();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		//�Ķ���� ���
		qna = adminQnaService.getParam(req, resp);

		// �Խù� �ҷ����� 
		qna = adminQnaService.view(qna);

		// ���� �𵨷� �����ϱ�
		req.setAttribute("qna", qna);		
		
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

		qna.setTitle(mul.getParameter("title"));
		
		qna.setContent(mul.getParameter("content"));
		
		file.setStored_name(mul.getFilesystemName("file"));
		System.out.println(mul.getFilesystemName("file"));
		file.setOrigin_name(mul.getOriginalFileName("file"));
		
		adminQnaService.update(qna);
		
		
		adminQnaService.deleteQnaFile(file);


		resp.sendRedirect("");
	
	}
}
