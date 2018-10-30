package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.user.User;
import service.user.AdminUserService;
import service.user.AdminUserServiceImpl;
import utils.Paging;


@WebServlet("/admin/user/list")
public class AdminUserListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AdminUserService adminUserService = new AdminUserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 확인
		boolean check = adminUserService.loginCheck(req);
				
		if(!check) {
			resp.sendRedirect("/user/login");
			return;
		}
		
		// 요청 파라미터 얻기 
		User user = adminUserService.getParam(req, resp);
		
		// 현재 페이지 얻어오기 
		int curPage = adminUserService.getCurPage(req);
		
		// 검색어 얻어오기 
		String search= adminUserService.getSearch(req);
		
		// 검색 조건 얻어오기 
		int searchType = 0;
		searchType= Integer.parseInt(req.getParameter("searchType"));
		
		// 전체 페이지 얻어오기 
		int totalCount = adminUserService.getTotalCount(search, searchType);
		
		// 페이징 객체 만들기 
		Paging paging = new Paging(totalCount, curPage);
		
		// 페이징 객체에 검색어 적용
		paging.setSearch(search);
		
		// 페이징 객체에 검색 조건 적용
		paging.setSearchType(searchType);
		
		// 회원 리스트 조회 
		List<User> list = adminUserService.getPagingList(paging);
		
		// 요청에 조회 결과 담기 
		req.setAttribute("userList", list);
		
		// 페이징 정보 요청에 담기 
		req.setAttribute("paging", paging);
		
		// view 페이지 지정
		req.getRequestDispatcher("/admin/user/userList.jsp").forward(req, resp);
	
	}

}
