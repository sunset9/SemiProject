package controller.main;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.plan.Plan;
import dto.user.User;
import service.contents.ContentsService;
import service.contents.ContentsServiceImpl;
import service.main.MainService;
import service.main.MainServiceImpl;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ContentsService conService = new ContentsServiceImpl();
	MainService mainService = new MainServiceImpl();
	User cUser = null;
	User cUserSocial = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 추천콘텐츠 리스트 가져오기
		List<Plan> recomPlanList = conService.getRecomList();

		// 최신콘텐츠 리스트 가져오기
		List<Plan> newestPlanList = conService.getNewList();
		System.out.println("MainController newestPlanList : "+newestPlanList);
		
		// 리스트 정보 저장하기
		req.setAttribute("recomPlanList", recomPlanList);
		req.setAttribute("newestPlanList", newestPlanList);

		// 뷰 지정해주기
		req.getRequestDispatcher("/main/main.jsp").forward(req, resp);
	}

//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// 한글 인코딩
//		req.setCharacterEncoding("UTF-8");
		
//		resp.sendRedirect("/plan/write");
//	}

}
