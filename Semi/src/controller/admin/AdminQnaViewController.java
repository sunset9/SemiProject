package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Qna;
import dto.board.QnaFile;
import service.board.AdminQnaService;
import service.board.AdminQnaServiceImpl;
import service.board.QnaService;
import service.board.QnaServiceImpl;



@WebServlet("/admin/qna/view")
public class AdminQnaViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminQnaService adminQnaService = new AdminQnaServiceImpl();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		// 파라미터 얻어오기
		Qna qna = adminQnaService.getParam(req, resp);

		// 게시물 상세보기 조회
		qna = adminQnaService.view(qna);
		
		// 게시글에 관련된 사진 불러 오기 
		QnaFile qnaFile = adminQnaService.viewFile(qna);
		
		// 조회 결과 요청에 담아 보내기
		req.setAttribute("qna", qna);
		req.setAttribute("qnaFile", qnaFile);
		
		// 작성자 닉네임 전달
		req.setAttribute("userNick", adminQnaService.getNick(qna));
		
		// 작성자 아이디 전달 
		req.setAttribute("userid", adminQnaService.getId(qna));
		
		// view 페이지 지정
		req.getRequestDispatcher("/admin/qna/view.jsp").forward(req, resp);
	}
}
