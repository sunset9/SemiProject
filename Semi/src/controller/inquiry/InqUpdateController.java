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
	private Inquiry inquiry = new Inquiry();
	private InqFile file = new InqFile();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		// 모델에 요청 파라미터 넣기 
		inquiry = inquiryService.getParam(req, resp);

		// 로그인 한 사람의 글이 아니면 중단하고 리스트로
		if( !inquiryService.checkWriter(req,inquiry) ) {
			resp.sendRedirect("/inquiry/list");
			return;
		}
		
		// 게시글 조회
		inquiry = inquiryService.view(inquiry);

		// 요청에 모델로 전달
		req.setAttribute("inquiry", inquiry);

		// 글 작성자 아이디 전달
		req.setAttribute("userid", inquiryService.getId(inquiry));
		
		
		// 글 작성자 닉네임 전달
		req.setAttribute("writerNick", inquiryService.getNick(inquiry));
		
		// 첨부파일 전달
		InqFile inqFile = inquiryService.viewFile(inquiry);
		req.setAttribute("inqFile", inqFile);
		
		
		// view 지정
		req.getRequestDispatcher("/inquiry/update.jsp").forward(req, resp);
		
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		inquiryService.update(req);

		resp.sendRedirect("/inquiry/list");
	
	}
   

}
