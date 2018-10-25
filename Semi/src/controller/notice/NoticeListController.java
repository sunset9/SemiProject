package controller.notice;

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
import service.board.NoticeService;
import service.board.NoticeServiceImpl;
import utils.Paging;


@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private NoticeService noticeService = new NoticeServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 현재 페이지 번호 얻기 
		int curPage = noticeService.getCurPage(req);
		
		// 검색어 얻기 
		String search = noticeService.getSearch(req);
		
		// 전체 페이지 얻어오기
		int totalCount = noticeService.getTotalCount(search);
		
		// 페이징 객체 생성
		Paging paging = new Paging(totalCount, curPage,10); 
		
		// 페이징 객체에 검색어 적용
		paging.setSearch(search);
		
		// 게시글 목록 MODEL로 추가하기
		List<Notice> list = noticeService.getPagingList(paging);
		
//		System.out.println(list);
		
		// 요청에 리스트 담아 보내기
		req.setAttribute("noticeList", list);
		
		// 페이징 객체 요청으로 보내기 
		req.setAttribute("paging", paging);
		
		// view 페이지 지정 
		req.getRequestDispatcher("/notice/list.jsp").forward(req, resp);
		
		
	}
}
