package controller.inquiry;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import dao.board.InqFileDao;
import dao.board.InqFileDaoImpl;
import dto.board.InqFile;
import dto.board.Inquiry;
import service.board.InquiryService;
import service.board.InquiryServiceImpl;


@WebServlet("/inquiry/update")
public class InqUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InquiryService inquiryService = new InquiryServiceImpl();
	private InqFileDao inqFileDao = new InqFileDaoImpl(); 
	private Inquiry inquiry = new Inquiry();
	private InqFile file = new InqFile();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		// 파라미터 받아오기 
		inquiry = inquiryService.getParam(req, resp);

		// 조회해오기
		inquiry = inquiryService.view(inquiry);

		// 모델에 담아 전달하기
		req.setAttribute("inquiry", inquiry);
		
		InqFile inqFile = inquiryService.viewFile(inquiry);
		
		// 보여줄 화면 지정
		req.getRequestDispatcher("").forward(req, resp);
		
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
	
		
		//--- MultipartRequest 생성자의 매개 변수 준비---
		// 1. 요청 객체 
		//	따로 만들 필요 없음 
		
		// 2. 파일 저장 위치
		// String 으로 서버의 실제 경로 지정 
		String saveDirectory = getServletContext().getRealPath("");
		
//		System.out.println( saveDirectory);
		
		// 3. 업로드 제한 사이즈 
		int maxPostSize = 1 *1024*1024; // 10MB 제한 
		
		// 4. 인코딩 
		// 업로드 정보 인코딩 방식 
		String encoding = "UTF-8";
		
		
		// 5. 중복 파일 이름 정책
		// DefaultFileRenamePolicy 는 중복파일이 있으면 
		// 파일 이름 뒤에 숫자를 추가하고 1부터 증가시킨다. 
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		
		//-----------------------------------------------
		
		// MultipartRequest 객체 생성 
		// 파일 업로드 처리 
		MultipartRequest mul = new MultipartRequest(req, saveDirectory, maxPostSize, encoding, policy);

		inquiry.setTitle(mul.getParameter("title"));
		
		inquiry.setContent(mul.getParameter("content"));
		
		file.setStored_name(mul.getFilesystemName("file"));
//		System.out.println(mul.getFilesystemName("file"));
		file.setOrigin_name(mul.getOriginalFileName("file"));
		
		inquiryService.update(inquiry);
		inquiryService.insertFile(file);


		resp.sendRedirect("/inquiry/list");
	
	}
   

}
