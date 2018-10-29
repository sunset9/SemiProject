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


@WebServlet("/admin/qna/update")
public class AdminQnaUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private AdminQnaService adminQnaService = new AdminQnaServiceImpl();
	private Qna qna = new Qna();
	private QnaFile file = new QnaFile();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		// 파라미터 얻어오기
		qna = adminQnaService.getParam(req, resp);

		// 게시글 상세보기 조회 
		qna = adminQnaService.view(qna);

		// 정보 요청에 담바 보내기
		req.setAttribute("qna", qna);	
		
		// 작성자 아이디 전달
		req.setAttribute("userid", adminQnaService.getId(qna));
				
		// 작성자 닉네임 전달 
		req.setAttribute("writerNick", adminQnaService.getNick(qna));
				
		// 첨부파일 전달
		file = adminQnaService.viewFile(qna);
		req.setAttribute("qnaFile", file);
		
		// view 페이지 지정
		req.getRequestDispatcher("/admin/qna/update.jsp").forward(req, resp);
		
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		adminQnaService.update(req);
		

		resp.sendRedirect("/admin/qna/list");
	
	}
}
