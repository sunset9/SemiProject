package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Notice;
import dto.board.NoticeFile;
import service.board.AdminNoticeService;
import service.board.AdminNoticeServiceImpl;
import service.board.NoticeService;
import service.board.NoticeServiceImpl;


@WebServlet("/admin/notice/view")
public class AdminNoticeViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminNoticeService adminNoticeService = new AdminNoticeServiceImpl();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		// 요청 파라미터 얻어오기
		Notice notice = adminNoticeService.getParam(req, resp);

		System.out.println("notice " + notice);

		// 게시글 상세 조회
		notice = adminNoticeService.view(notice);

		// 게시글에 관련된 사진 불러오기 
		NoticeFile noticeFile = adminNoticeService.viewFile(notice);
		System.out.println(noticeFile);
				
		// 조회결과 요청에 담아 보내기 
		req.setAttribute("notice", notice);
		
		// 첨부파일 요청에 담아 보내기
		req.setAttribute("noticeFile", noticeFile);
		
		// 글 작성자 닉네임 전달
		req.setAttribute("userNick", adminNoticeService.getNick(notice));
		
		// 글 작성자 아이디 전달 
		req.setAttribute("userid", adminNoticeService.getId(notice));
		
		// view 페이지 지정
		req.getRequestDispatcher("/admin/notice/view.jsp").forward(req, resp);
	}
}
