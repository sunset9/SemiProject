package controller.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Qna;
import dto.board.QnaFile;
import service.board.QnaService;
import service.board.QnaServiceImpl;



@WebServlet("/qna/view")
public class QnaViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private QnaService qnaService = new QnaServiceImpl();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		// 파라미터 얻어오기
		Qna qna = qnaService.getParam(req, resp);

		// 게시글 상세 보기 =
		qna = qnaService.view(qna);

		// 파일 불러오기
		QnaFile qnaFile = new QnaFile();
		qnaFile = qnaService.viewFile(qna);
		
		// 파일 요청에 담아 보내깅 
		req.setAttribute("qnaFile", qnaFile);
		
		// 게시물 작성자 아이디 전달해주기
		req.setAttribute("userid", qnaService.getId(qna));
		
		// 게시글 요청에 담아 보내기
		req.setAttribute("qna", qna);
		
		//view 지정
		req.getRequestDispatcher("/qna/view.jsp").forward(req, resp);
	}
}
