package controller.story;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.plan.Plan;
import dto.story.Comment;
import service.stroy.StoryService;
import service.stroy.StoryServiceImpl;

/**
 * Servlet implementation class storyCommentViewController
 */
@WebServlet("/story/comment/view")
public class storyCommentViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StoryService sService = new StoryServiceImpl();
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.setCharacterEncoding("utf-8");
	
			
			Plan plan = new Plan();
			
			
			String planidx = req.getParameter("plan_idx");
			
			if (planidx != null && !planidx.equals("")) {
				plan.setPlan_idx(Integer.parseInt(planidx));
			}
			else {
				System.out.println("planidx값이 이상함");
			}
			
			
			String storyIdx = req.getParameter("story_idx");
			
			List<Comment> CommentList = sService.getCommentList(plan);
			
			req.setAttribute("commentList", CommentList);
			req.setAttribute("story_idx", storyIdx);
			req.getRequestDispatcher("/plan/story/storyCommentView.jsp").forward(req, resp);
		}

}
