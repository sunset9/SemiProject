package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.plan.Plan;
import dto.story.Comment;
import service.stroy.AdminStoryService;
import service.stroy.AdminStoryServiceImpl;
import utils.Paging;

@WebServlet("/admin/comment/list")
public class AdminCommentListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	AdminStoryService adminStoryService = new AdminStoryServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 확인
		boolean check = adminStoryService.loginCheck(req);
				
		if(!check) {
			resp.sendRedirect("/user/login");
			return;
		}
		
		// 현재 페이지 얻어오기 
		int curPage = adminStoryService.getCurPage(req);
				
		// 검색어 얻어오기 
		String search = adminStoryService.getSearch(req);
				
//		System.out.println("search : " +search);

		// 전체 페이지 얻어오기 
		int totalCount = adminStoryService.getTotalCount(search);
				
		// 페이징 객체 생성 
		Paging paging = new Paging(totalCount, curPage,10);
				
		// 페이징 객체에 검색어 적용 
		paging.setSearch(search);
				
		// 게시글 리스트 조회 
		List<Comment> list = adminStoryService.getPagingList(paging);
		
		System.out.println("controller list: "+list);
				
		// 요청에 조회 결과 담기 
		req.setAttribute("commList", list);
				
		// 페이징 결과 요청에 담기 
		req.setAttribute("paging", paging);
				
		// view 페이지 지정
		req.getRequestDispatcher("/admin/comment/clist.jsp").forward(req, resp);
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	
	}
}
