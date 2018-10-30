package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Inquiry;
import service.board.AdminInquiryService;
import service.board.AdminInquiryServiceImpl;
import service.board.InquiryService;
import service.board.InquiryServiceImpl;
import utils.Paging;


@WebServlet("/admin/main")
public class AdminMainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminInquiryService adminInquiryService = new AdminInquiryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 확인
		boolean check = adminInquiryService.loginCheck(req);
				
		if(!check) {
			resp.sendRedirect("/user/login");
			return;
		}
		
		// 현재 페이지 번호 얻기 
		int curPage = adminInquiryService.getCurPage(req);
		
		//검색어 얻기 
		String search = adminInquiryService.getSearch(req);
		
		// 전체 게시물 수 얻기 
		int totalCount = adminInquiryService.getTotalCount(search);
				
		// 페이징 객체 생성
		Paging paging = new Paging (totalCount, curPage);
		
		// 페이징 객체에 검색어 적용 
		paging.setSearch(search);
		
		// 게시글 목록 조회후 요청에 담기 
		// 답변이 안된 문의사항을 조회해야함
		List<Inquiry> list = adminInquiryService.getMainPagingList(paging);
		req.setAttribute("inqList", list);
		
//		System.out.println(list);
		
		// 페이징 객체 요청에 담기 
		req.setAttribute("paging", paging);
		
		// view 페이지 지정
		req.getRequestDispatcher("/admin/adminMain.jsp").forward(req, resp);
	}
}
