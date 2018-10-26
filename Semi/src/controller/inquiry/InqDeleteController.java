package controller.inquiry;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dto.board.Inquiry;
import dto.board.Reply;
import service.board.InquiryService;
import service.board.InquiryServiceImpl;


@WebServlet("/inquiry/delete")
public class InqDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private InquiryService inquiryService = new InquiryServiceImpl();
	private Inquiry inquiry = new Inquiry();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 요청 파라미터 받아오기 
		inquiry = inquiryService.getParam(req, resp);
		
		// 로그인 한 사람의 글이 아니면 중단하고 리스트로
		if( !inquiryService.checkWriter(req,inquiry) ) {
			resp.sendRedirect("/inquiry/list");
			return;
		}
		
		
		// 게시글 삭제
		inquiryService.delete(inquiry);
		
				
		// 문의사항 리스트로 리다이렉트 
		resp.sendRedirect("/inquiry/list");
	}
   

}
