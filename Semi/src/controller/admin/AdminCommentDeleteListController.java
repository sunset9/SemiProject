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

@WebServlet("/admin/comment/deleteList")
public class AdminCommentDeleteListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	AdminStoryService adminStoryService = new AdminStoryServiceImpl();
	private Comment comment = new Comment();
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String names  = req.getParameter("names");
		
		if(!"".equals(names) && names != null) {
			adminStoryService.commListDelete(names);
		}
		
		resp.sendRedirect("/admin/comment/list");
	
	}
	
	
}
