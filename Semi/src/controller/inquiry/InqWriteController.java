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
		req.setCharacterEncoding("utf-8");
	
		// 로그인 되어있지 않으면 main 으로 리다이렉트 하기
//		if( req.getSession().getAttribute("login")==null) {
//			resp.sendRedirect("/main");
//		}
		
		// VIEW 페이지 지정
		req.getRequestDispatcher("/inquiry/write.jsp").forward(req, resp);
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 요청 파라미터 한글 인코딩 설정 : UTF-8
		req.setCharacterEncoding("utf-8");

		inquiryService.write(req);
	
		
	
	
	}
}
