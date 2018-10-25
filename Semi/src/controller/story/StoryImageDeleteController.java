package controller.story;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class imageDeleteServlet
 */
@WebServlet("/story/image_delete")
public class StoryImageDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		System.out.println(request.getParameter("src"));
		
		ServletContext context = getServletContext();
		
		String filePath = context.getRealPath("upload/story")+"\\";
//		String filePath = context.getRealPath("upload/story");
		
		System.out.println("filepath:"+filePath);
	    String[] fileName = request.getParameter("src").split("/");
	    
	    filePath += fileName[5];
	    	
	    File f = new File(filePath); // 파일 객체생성
	    
	    System.out.println("file:"+f);
	    
	    if( f.exists()) f.delete(); // 파일이 존재하면 파일을 삭제한다.

	}

}
