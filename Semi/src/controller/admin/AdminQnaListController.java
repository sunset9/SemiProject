package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Qna;
import service.board.AdminQnaService;
import service.board.AdminQnaServiceImpl;
import utils.Paging;



@WebServlet("/admin/qna/list")
public class AdminQnaListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminQnaService adminQnaService = new AdminQnaServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 현재 페이지 얻어오기 
		int curPage = adminQnaService.getCurPage(req);
		
		// 검색어 얻어 오기 
		String search = adminQnaService.getSearch(req);
		
		// 전체 페이지 수 얻어오기 
		int totalCount = adminQnaService.getTotalCount(search);
		
		// 페이징 객체 만들기
		Paging paging = new Paging(totalCount, curPage); 
		
		// 페이징 객체에 검색어 적용
		paging.setSearch(search);
		
		// 게시물 리스트 조회
		List<Qna> list = adminQnaService.getPagingList(paging);
		
		// 리스트 조회 요청에 담아 보내기 
		req.setAttribute("qnaList", list);
		
		// 페이징 객체 요청에 담아 보내기
		req.setAttribute("paging", paging);
		
		// view 페이지 지정
		req.getRequestDispatcher("/admin/qna/list.jsp").forward(req, resp);
		
		
	}
}


































