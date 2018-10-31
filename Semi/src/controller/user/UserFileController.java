package controller.user;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import dao.user.UserDao;
import dao.user.UserDaoImpl;
import dto.user.UploadFile;
import dto.user.User;

/**
 * Servlet implementation class UserFileController
 */
@WebServlet("/user/file")
public class UserFileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.getRequestDispatcher("/user/update.jsp").forward(req, resp);
		}
	
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			//System.out.println("user file controller 도착");
			// 인코딩 설정 
			req.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html;charset=utf-8");
			
			// 응답객체 출력 스트림
			PrintWriter out = resp.getWriter();
			
			// MultipartRequest 생성자 매개변수 준비 시작
			// 1. 요청 객체는 이미 있음.
			
			// 2. 파일 저장 위치 - String으로 서버의 실제 경로 지정
			ServletContext context = getServletContext();
			String saveDirectory = context.getRealPath("upload/user");
			System.out.println(saveDirectory);
			
			// 3. 업로드 제한 사이즈 
			int maxPostSize = 10 * 1024 * 1024; //10MB
			
			// 4. 인코딩 - 업로드 정보 인코딩 방식 
			String encoding = "UTF-8";
			
			// 5. 중복 파일 이름 정책 
			//		DefaultFileRenamePolicy는 중복파일이 있으면
			//		파일이름 뒤에 숫자를 추가하고 1부터 증가시킨다.
			FileRenamePolicy policy = new DefaultFileRenamePolicy();
			// 매개변수 준비 끝
			
			
			// MultipartRequest 객체 생성 시작 
			// 파일 업로드 처리 시작
			MultipartRequest mul = new MultipartRequest(
					req,
					saveDirectory,
					maxPostSize,
					encoding,
					policy
					);
			
			
			
			// 업로드 정보 관리
//			out.println("--- 전달 파라미터 ---<br>");
//			out.println( mul.getParameter("title") );
//			
//			out.println("<br><br>--- 업로드파일 ---<br>");
//			File up = mul.getFile("uploadFile");
//			out.println( up.toString() );
//			
//			out.println("<br><br>--- 저장된 파일이름 ---<br>");
//			out.println( mul.getFilesystemName("uploadFile"));
//			
//			out.println("<br><br>--- 원본 파일이름 ---<br>");
//			out.println( mul.getOriginalFileName("uploadFile"));
//			
//			out.println("<br><br>--- 파일 형식 ---<br>");
//			out.println( mul.getContentType("uploadFile"));

			UploadFile uploadFile = new UploadFile();
			
			// 저장된 파일 이름 
			uploadFile.setStored_name(mul.getFilesystemName("uploadFile"));
			
			// 원본 파일 이름 
			uploadFile.setOrigin_name(mul.getOriginalFileName("uploadFile"));
			
			UserDao userDao = new UserDaoImpl();
			userDao.insert(uploadFile);
			
			//req.setAttribute("fileName", uploadFile.getStored_name());
			//System.out.println(uploadFile.getStored_name());
			req.getSession().setAttribute("fileName", uploadFile.getStored_name());
			
			
			String fullpath = saveDirectory+"/"+uploadFile.getStored_name();
			System.out.println("userFileController : "+fullpath);
			
			User cUser = (User) req.getSession().getAttribute("user");
			cUser.setProfile(fullpath);
			
			req.getSession().setAttribute("user", cUser);
			
			
			
			resp.sendRedirect("/user/update");
		}
}
