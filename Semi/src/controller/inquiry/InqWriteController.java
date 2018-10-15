package controller.inquiry;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.board.InqFileDao;
import dao.board.InqFileDaoImpl;
import dto.board.InqFile;
import dto.board.Inquiry;
import service.board.InquiryService;
import service.board.InquiryServiceImpl;




@WebServlet("/inquiry/write")
public class InqWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private InquiryService inquiryService = new InquiryServiceImpl();
	private Inquiry inquiry = new Inquiry();
	private InqFile file = new InqFile();
	private InqFileDao inqfileDao = new InqFileDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 인코딩 설정
		req.setCharacterEncoding("utf-8");
	
		// 세션에서 로그인 정보 확인후 글 쓰기 가능
		
		// 보여줄 페이지 설정 
		req.getRequestDispatcher("").forward(req, resp);
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");

	
		//--- MultipartRequest 생성자의 매개 변수 준비---
		// 1. 요청 객체 
		//	따로 만들 필요 없음 
		
		// 2. 파일 저장 위치
		// String 으로 서버의 실제 경로 지정 
		
		// 3. 업로드 제한 사이즈 
		
		// 4. 인코딩 
		// 업로드 정보 인코딩 방식 
		
		
		// 5. 중복 파일 이름 정책
		// DefaultFileRenamePolicy 는 중복파일이 있으면 
		// 파일 이름 뒤에 숫자를 추가하고 1부터 증가시킨다. 
		
		//-----------------------------------------------
		
		// MultipartRequest 객체 생성 
		// 파일 업로드 처리 
		
		
		inquiryService.write(inquiry);
		
		inquiryService.insertFile(file);
		


		// 보여줄 화면 지정
		resp.sendRedirect("");
	
	
	}
}
