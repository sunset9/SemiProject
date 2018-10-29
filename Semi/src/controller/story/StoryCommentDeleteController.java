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
import service.plan.PlanService;
import service.plan.PlanServiceImpl;
import service.stroy.StoryService;
import service.stroy.StoryServiceImpl;

@WebServlet("/story/comment/delete")
public class StoryCommentDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PlanService pService = new PlanServiceImpl();
	StoryService sService = new StoryServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		Comment comm = new Comment();
		
		String commIdx_str = req.getParameter("comm_idx");
		
		
		
		if (commIdx_str != null && !commIdx_str.equals("")) {
			
			
			comm.setComm_idx(Integer.parseInt(commIdx_str));
			sService.deleteComment(comm);
			
		}else {
			System.out.println("comm_idx 값이 잘못됨");
		}
		
		

		
		String storyIdx = req.getParameter("story_idx");
		
		if (storyIdx != null && !storyIdx.equals("")) {
			
			req.setAttribute("story_idx", storyIdx);
		}
		else {
			System.out.println("story_idx값이 이상함");
		}
		
		
		Plan plan = new Plan();
		
		
		String planidx = req.getParameter("plan_idx");
		
		if (planidx != null && !planidx.equals("")) {
			plan.setPlan_idx(Integer.parseInt(planidx));
		}
		else {
			System.out.println("planidx값이 이상함");
		}
		
		List<Comment> CommentList = sService.getCommentList(plan);
		
		req.setAttribute("commentList", CommentList);
		
		
		req.getRequestDispatcher("/plan/story/storyCommentView.jsp").forward(req, resp);
		
	
	}
}
