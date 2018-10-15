package controller.qna;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.board.QnaDao;
import dao.board.QnaDaoImpl;
import dto.board.Qna;
import service.board.QnaService;
import service.board.QnaServiceImpl;
import utils.Paging;



@WebServlet("/qna/list")
public class QnaListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private QnaService qnaService = new QnaServiceImpl();
	private QnaDao qnaDao = new QnaDaoImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//----- 페이징 작업 -----
		// 현재 페이지 번호 얻기
		int curPage = qnaService.getCurPage(req);
		
		// 페이징 객체
		int totalCount = qnaService.getTotalCount();
		
		Paging paging = new Paging(totalCount, curPage,10); 
		
		// List에 조회 결과 담기
		List<Qna> list = qnaService.getPagingList(paging);
		
		// 요청에 결과 담아서 보내기
		req.setAttribute("qnalist", list);
		
		// 페이징 객체 모델로 추가 하기
		req.setAttribute("paging", paging);
		
		// 보여줄 화면 지정
		req.getRequestDispatcher("").forward(req, resp);
		
		
	}
}


































