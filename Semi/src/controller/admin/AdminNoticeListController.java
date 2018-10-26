package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.board.NoticeDao;
import dao.board.NoticeDaoImpl;
import dto.board.Notice;
import service.board.AdminNoticeService;
import service.board.AdminNoticeServiceImpl;
import service.board.NoticeService;
import service.board.NoticeServiceImpl;
import utils.Paging;




@WebServlet("/admin/notice/list")
public class AdminNoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminNoticeService adminNoticeService = new AdminNoticeServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 현재 페이지 얻어오기 
		int curPage = adminNoticeService.getCurPage(req);
		
		// 검색어 얻어 오기 
		String search =  adminNoticeService.getSearch(req);
		
//		System.out.println("search:"+search);
		
		// 전체 페이지 수 얻어오기  
		int totalCount = adminNoticeService.getTotalCount(search);

		// 페이징 객체 만들기 
		Paging paging = new Paging(totalCount, curPage,10); 
		
		// 페이징 객페에 검색어 적용 
		paging.setSearch(search);

		// 게시물 리스트 조회
		List<Notice> list = adminNoticeService.getPagingList(paging);
		
		
//		System.out.println("paging.getSearch()"+paging.getSearch());
		
//		System.out.println(list);
		
		// 조회 결과 요청에 담기
		req.setAttribute("noticeList", list);
		
		// 페이징 결과 요청에 담기
		req.setAttribute("paging", paging);
		
		// view 페이지 지정
		req.getRequestDispatcher("/admin/notice/list.jsp").forward(req, resp);
		
		
	}
}
