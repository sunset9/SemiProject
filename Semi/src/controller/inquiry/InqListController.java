package controller.inquiry;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Inquiry;
import service.board.InquiryService;
import service.board.InquiryServiceImpl;
import utils.Paging;



@WebServlet("/inquiry/list")
public class InqListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private InquiryService inquiryService = new InquiryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		// 현재 페이지 번호 얻기 
		int curPage = inquiryService.getCurPage(req);

		// 전체 게시물 수 얻기 
		int totalCount = inquiryService.getTotalCount();
		
		// 페이징 객체 생성
		Paging paging = new Paging(totalCount, curPage,10); 

//		System.out.println(paging);
		
		//게시글 목록 MODEL로 추가 하기 
		List<Inquiry> list = inquiryService.getPagingList(paging);
		req.setAttribute("inquirylist", list);

		// 페이징 객체 MODEL로 추가
		req.setAttribute("paging", paging);

		// VIEW 페이지 지정
		req.getRequestDispatcher("/inquiry/inquiryList.jsp").forward(req, resp);
		
		

	}
}
