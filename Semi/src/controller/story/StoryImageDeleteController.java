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

		ServletContext context = getServletContext();
		
		//파일경로
		String filePath = context.getRealPath("upload/story")+"\\";
		
		//파일이름 구하기
	    String[] fileName = request.getParameter("src").split("/");
	    
	    //삭제할 파일 경로 + 파일이름
	    filePath += fileName[5];
	    	
	    File f = new File(filePath); // 파일 객체생성
	    
	    if( f.exists()) f.delete(); // 파일이 존재하면 파일을 삭제한다.

	}

}
