package controller.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Inquiry;
import dto.plan.Plan;
import dto.user.Bookmark;
import dto.user.User;
import service.board.InquiryService;
import service.board.InquiryServiceImpl;
import service.user.UserService;
import service.user.UserServiceImpl;
import utils.Paging;

/**
 * Servlet implementation class UserUpdateController
 */
@WebServlet("/user/myPage")
public class UserMypageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserService userService = new UserServiceImpl();
	InquiryService inqService = new InquiryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//현재 유저 정보 가져오기
		User cUser = (User) req.getSession().getAttribute("user");
		//System.out.println("마이페이지 현재 세션에 있는 유저 : "+cUser);
		User cUserSocial = (User) req.getSession().getAttribute("socialUser");
		//System.out.println("마이페이지 현재 세션에 있는 소셜 유저 : "+cUserSocial);
		
		if(cUserSocial == null) {
			System.out.println("아이디 로그인 유저");

			// 총 게시물 수, 총 여행거리 정보 추가된 user 객체 얻기
			User user = userService.getUseraddedInfo(cUser);
			req.setAttribute("user", user);
			
			//현재 유저의 일정들 가져오기
			List<Plan> plannerList = userService.getPlanner(user);
			//planList
			req.setAttribute("plannerList", plannerList);
			System.out.println("유저마이페이지컨트롤러에서 plannerList : "+plannerList);
			
			
			//모든 일정 가져오기
			List<Plan> allPlanList = userService.getAllPlanList(user);
			//System.out.println("userMyPageController allPlanList : "+allPlanList);
			req.setAttribute("allPlanList", allPlanList);
			
			//현재 유저의 북마크 가져오기
			List<Bookmark> bookMarkList = userService.getBookmarkList(user);
			//bookMarkList
			req.setAttribute("bookMarkList", bookMarkList);
			//System.out.println("mypagecontroller bList 잘 가져왔나 : "+bookMarkList); --> OK
			
//---------------------------------------------------------------------------------------------------
			
			// 현재 페이지 번호 얻기 
			int curPage = inqService.getCurPage(req);
			
			//검색어 얻기 
			String search = null;
			
			// 전체 게시물 수 얻기 
			int totalCount = inqService.getTotalCount(search);
					
			// 페이징 객체 생성
			Paging paging = new Paging (totalCount, curPage);
			
			// 페이징 객체에 검색어 적용 
			paging.setSearch(search);
			
			// 내가 문의한 게시물 불러오기 
			List<Inquiry> inqList = inqService.getPagingMyList(paging);
			
			req.setAttribute("inqList", inqList);
			
			// 페이징 객체 요청에 담기 
			req.setAttribute("paging", paging);
			
			
		} else if(cUser == null) {
			System.out.println("소셜 로그인 유저");
			
			// 총 게시물 수, 총 여행거리 정보 추가된 user 객체 얻기
			User user = userService.getUseraddedInfo(cUserSocial);
			req.setAttribute("socialUser", user);
			System.out.println("userMypage "+user);
//			req.setAttribute("socialUser", cUserSocial);
			
			//현재 유저의 일정들 가져오기
			List<Plan> plannerList = userService.getPlanner(user);
			//planList
			req.setAttribute("plannerList", plannerList);
			

			
			//모든 일정 가져오기
			List<Plan> allPlanList = userService.getAllPlanList(user);
			//System.out.println("userMyPageController allPlanList : "+allPlanList);
			req.setAttribute("allPlanList", allPlanList);

			
			//현재 유저의 북마크 가져오기
			List<Bookmark> bookMarkList = userService.getBookmarkList(user);
			//bookMarkList
			req.setAttribute("bookMarkList", bookMarkList);
			//System.out.println("mypagecontroller bList 잘 가져왔나 : "+bookMarkList); --> OK
			
			// 내가 한 문의사항 가져오기
			
			
			
		}
		
		//정보수정페이지(마이페이지)로 이동
		req.getRequestDispatcher("/user/myPage.jsp").forward(req, resp);
	}
	
	
}
