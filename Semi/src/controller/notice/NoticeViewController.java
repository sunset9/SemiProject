package controller.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Notice;
import service.board.NoticeService;
import service.board.NoticeServiceImpl;


@WebServlet("/notice/view")
public class NoticeViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NoticeService noticeService = new NoticeServiceImpl();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		// 파라미터 받아오기 
		Notice notice = noticeService.getParam(req, resp);

		// 게시물 상세보기
		notice = noticeService.view(notice);

		// 정보 모델에 담아 전달
		req.setAttribute("notice", notice);
		
		// 보여줄 화면 지정
		req.getRequestDispatcher("").forward(req, resp);
	}
}
