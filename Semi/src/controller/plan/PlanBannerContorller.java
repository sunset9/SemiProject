package controller.plan;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * Servlet implementation class PlanBannerContorller
 */
@WebServlet("/plan/banner")
public class PlanBannerContorller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println();
		System.out.println("----- PlanBannerContorller -----");
		
		ServletContext context = getServletContext();
		String saveDirectory = context.getRealPath("upload/banner");
		System.out.println(saveDirectory);
//				
		int maxPostSize = 10 * 1024 * 1024; //10MB
//				
		String encoding = "UTF-8";
//				
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
//				
		MultipartRequest mul = new MultipartRequest(
				request,
				saveDirectory,
				maxPostSize,
				encoding,
				policy
				);
		
		int plan_idx = (int)request.getSession().getAttribute("plan_idx");
		int user_idx = (int)request.getSession().getAttribute("user_idx");
		
		request.setAttribute("plan_idx", plan_idx);
		request.setAttribute("user_idx", user_idx);
		
		String path = "/plan?plan_idx="+plan_idx+"&user_idx="+user_idx;
		System.out.println("-------------------------------------------");
		request.getRequestDispatcher(path).forward(request, response);

	}

}
