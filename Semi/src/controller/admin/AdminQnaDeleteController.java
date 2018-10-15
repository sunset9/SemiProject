package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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



@WebServlet("admin/qna/delete")
public class AdminQnaDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminQnaService adminQnaService = new AdminQnaServiceImpl();
	private Qna qna = new Qna();
	private QnaFile file = new QnaFile();
   

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 파라미터 얻어오기
		qna = adminQnaService.getParam(req, resp);
		
		// Qna 삭제하기 
		adminQnaService.delete(qna);
		
		// 삭제 파일 지정하기 
		file.setQna_idx(qna.getQna_idx());
		
		// Qna에 관련된 파일 삭제ㄴ
		adminQnaService.deleteQnaFile(file);
		
		resp.sendRedirect("");
	}
   

}
