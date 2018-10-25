package controller.contents;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AllContentsCtroller
 */
@WebServlet("/contents/all")
public class AllContentsCtroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.getRequestDispatcher("/user/allContents.jsp").forward(req, resp);
		}
	
	
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//요청 파리미터 받기 
			String category = req.getParameter("category");
			String searchValue = req.getParameter("searchValue");
			
			System.out.println("올콘텐츠 컨트롤러 : "+category); //오키 
			System.out.println("올콘텐츠 컨트롤러 : "+searchValue); //오키
		
		}
	
	
}
