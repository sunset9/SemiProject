package controller.contents;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Inquiry;
import dto.plan.Plan;
import dto.user.Search;
import service.board.InquiryService;
import service.board.InquiryServiceImpl;
import service.contents.ContentsService;
import service.contents.ContentsServiceImpl;
import utils.Paging;

/**
 * Servlet implementation class AllContentsCtroller
 */
@WebServlet("/contents/all")
public class AllContentsCtroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ContentsService conService = new ContentsServiceImpl();

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		System.out.println("allcontentcontroll");
		// 현재 페이지 번호 얻기 (처음엔 0을 반환받아서 1페이지가 기본으로 뜸)
		int curPage = conService.getCurPage(req);

		// 검색타입 얻기
		int searchType =0;
		
		if(req.getParameter("searchType")!= null) {
			searchType =Integer.parseInt(req.getParameter("searchType"));
		}
		
		// 검색어 얻기
		String search = conService.getSearch(req);

		// 검색한 게시물 수 얻기
		int totalCount = conService.getTotalCount(searchType, search);

		// 페이징 객체 생성
		Paging paging = new Paging(totalCount, curPage, 9);

		// 페이징 객체에 검색어 적용
		paging.setSearch(search);
		paging.setSearchType(searchType);
		
		System.out.println(paging);

		// 게시글 목록 MODEL로 추가 하기
		List<Plan> planList = conService.getPagingList(paging);
		req.setAttribute("planList", planList);

		// 페이징 객체 MODEL로 추가
		req.setAttribute("paging", paging);
		
		
		req.getRequestDispatcher("/contents/allContents.jsp").forward(req, resp);
	}
	
	
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			
		}
	
	
}
