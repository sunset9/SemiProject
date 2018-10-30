package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import dao.board.NoticeFileDao;
import dao.board.NoticeFileDaoImpl;
import dto.board.Notice;
import dto.board.NoticeFile;
import service.board.AdminNoticeService;
import service.board.AdminNoticeServiceImpl;
import service.board.NoticeService;
import service.board.NoticeServiceImpl;




@WebServlet("/admin/notice/update")
public class AdminNoticeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	private AdminNoticeService adminNoticeService = new AdminNoticeServiceImpl();
	private Notice notice = new Notice();
	private NoticeFile file = new NoticeFile();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 로그인 확인
		boolean check = adminNoticeService.loginCheck(req);
				
		if(!check) {
			resp.sendRedirect("/user/login");
			return;
		}
		
		// 요청 파라미터 얻어오기
		notice = adminNoticeService.getParam(req, resp);

		// 게시글 조회
		notice = adminNoticeService.view(notice);

		// 요청 파라미로 정보 전달
		req.setAttribute("notice", notice);		
		
		// 작성자 아이디 전달
		req.setAttribute("userid", adminNoticeService.getId(notice));
		
		// 작성자 닉네임 전달 
		req.setAttribute("writerNick", adminNoticeService.getNick(notice));
		
		// 첨부파일 전달
		file = adminNoticeService.viewFile(notice);
		req.setAttribute("noticeFile", file);
						
		// view 페이지 지정
		req.getRequestDispatcher("/admin/notice/update.jsp").forward(req, resp);
		
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
	
		
		adminNoticeService.update(req);
		
		resp.sendRedirect("/admin/notice/list");
	
	}
}
