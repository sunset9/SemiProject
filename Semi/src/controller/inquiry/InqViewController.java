package controller.inquiry;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.board.InqFileDao;
import dao.board.InqFileDaoImpl;
import dao.board.ReplyDao;
import dao.board.ReplyDaoImpl;
import dto.board.InqFile;
import dto.board.Inquiry;
import dto.board.Reply;
import service.board.InquiryService;
import service.board.InquiryServiceImpl;


@WebServlet("/inquiry/view")
public class InqViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InquiryService inquiryService = new InquiryServiceImpl();
	private ReplyDao replyDao = new ReplyDaoImpl();
	private InqFileDao fileDao = new InqFileDaoImpl();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		// 요청파라미터 모델에 담기 
		Inquiry inquiry = inquiryService.getParam(req, resp);

		// 게시글 상세 조회  
		inquiry = inquiryService.view(inquiry);
		System.out.println(inquiry);
		
		// 게시글에 관련된 댓글 리스트 불러오기
		List<Reply> repList = replyDao.selectInqByInqIdx(inquiry);
		
		// 게시글에 관련된 사진 불러오기
		InqFile inqFile = fileDao.selectByInqIdx(inquiry);
		
		// 게시글 모델 전달
		req.setAttribute("inquiry", inquiry);
		
		System.out.println(inquiry);
		
		// 글 작성자 이메일 전달
		req.setAttribute("userid", inquiryService.getId(inquiry));
		
		// 글 작성자 닉네임 전달
		req.setAttribute("writerNick", inquiryService.getNick(inquiry));
		
		// 첨부파일 모델 전달
		req.setAttribute("inqFile", inqFile);
		
		// 댓글 리스트 전달
		req.setAttribute("repList", repList);
		
		// VIEW 지정
		req.getRequestDispatcher("/inquiry/view.jsp").forward(req, resp);
	}
}
