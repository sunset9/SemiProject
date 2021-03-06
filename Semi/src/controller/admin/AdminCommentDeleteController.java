package controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.story.Comment;
import service.plan.PlanService;
import service.plan.PlanServiceImpl;
import service.stroy.AdminStoryService;
import service.stroy.AdminStoryServiceImpl;
import service.stroy.StoryService;
import service.stroy.StoryServiceImpl;

@WebServlet("/admin/comment/delete")
public class AdminCommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	AdminStoryService adminStoryService = new AdminStoryServiceImpl();
	private Comment comment = new Comment();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 로그인 확인
		boolean check = adminStoryService.loginCheck(req);
				
		if(!check) {
			resp.sendRedirect("/user/login");
			return;
		}
		
		// 파라미터 값 받아오기
		comment = adminStoryService.getParam(req);
		
		// 게시물 삭제 하기
		boolean s = adminStoryService.deleteComment(comment);
		
		resp.getWriter().append("{\"success\":"+s+"}");
	
	
	}
	
	
}
