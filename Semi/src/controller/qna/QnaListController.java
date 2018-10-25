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

		// 현재 페이지 번호 얻기
		int curPage = qnaService.getCurPage(req);
		
		// 검색어 얻기 
		String search = qnaService.getSearch(req);
		
		// 전체 페이지 얻어오기
		int totalCount = qnaService.getTotalCount(search);
		
		// 페이징 객체 생성
		Paging paging = new Paging(totalCount, curPage,10); 
		
		// 페이징 객체에 검색어 적용 하기 
		paging.setSearch(search);
		
		// 게시글 조회해오기
		List<Qna> list = qnaService.getPagingList(paging);
		
		System.out.println(list);
		
		// 요청에 조회 결과 담아 보내기
		req.setAttribute("qnaList", list);
		
		// 페이징 객체 요청으로 보내기
		req.setAttribute("paging", paging);
		
		// view 페이지 지정
		req.getRequestDispatcher("/qna/list.jsp").forward(req, resp);
		
		
	}
}


































