package controller.main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.main.MainService;
import service.main.MainServiceImpl;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MainService mainService = new MainServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//추천콘텐츠 리스트 가져오기
		List recomPlanList = mainService.getRecommendedContents();
		
		//최신콘텐츠 리스트 가져오기
		List newestPlanList = mainService.getNewestContents();
		
		//리스트 정보 저장하기
		req.setAttribute("recomPlanList", recomPlanList);
		req.setAttribute("newestPlanList", newestPlanList);
		  
		//뷰 지정해주기
		req.getRequestDispatcher("/main/main.jsp").forward(req, resp);
	}

}


