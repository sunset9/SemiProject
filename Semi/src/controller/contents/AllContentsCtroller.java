package controller.contents;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.plan.Plan;
import dto.user.Search;
import service.contents.ContentsService;
import service.contents.ContentsServiceImpl;

/**
 * Servlet implementation class AllContentsCtroller
 */
@WebServlet("/contents/all")
public class AllContentsCtroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ContentsService conService = new ContentsServiceImpl();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.getRequestDispatcher("/contents/allContents.jsp").forward(req, resp);
		}
	
	
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//Search search = new Search();
		
			
			//요청 파리미터 받기 
			String category = req.getParameter("category");
			String searchValue = req.getParameter("searchValue");

			System.out.println("올콘텐츠 컨트롤러 카테고리 : "+category); //오키 
			System.out.println("올콘텐츠 컨트롤러 검색값: "+searchValue); //오키
			
			
			//콘텐츠 리스트 가져오기
			List<Plan> searchList = conService.getList(category, searchValue);
			
			//setAttribute
			req.setAttribute("searchList", searchList);
			
			System.out.println("올콘텐츠컨트롤러 검색결과: "+searchList);
			req.getRequestDispatcher("/contents/allContents.jsp").forward(req, resp);
			
		}
	
	
}
