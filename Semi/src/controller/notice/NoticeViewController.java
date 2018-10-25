package controller.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Notice;
import dto.board.NoticeFile;
import service.board.NoticeService;
import service.board.NoticeServiceImpl;


@WebServlet("/notice/view")
public class NoticeViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoticeService noticeService = new NoticeServiceImpl();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		// 파라미터얻어오기
		Notice notice = noticeService.getParam(req, resp);

		// 게시글 상세 보기
		notice = noticeService.view(notice);
		
		// 파일 불러오기
		NoticeFile noticeFile = new NoticeFile();
		noticeFile = noticeService.viewFile(notice);

		// 파일 모델 전달 
		req.setAttribute("noticeFile", noticeFile);
		
		// 글 작성자 아이디 전달 
		req.setAttribute("userid", noticeService.getId(notice));
		
		// 게시글 모델로 전달하기
		req.setAttribute("notice", notice);
		
		// view 지정
		req.getRequestDispatcher("/notice/view.jsp").forward(req, resp);
	}
}
