package controller.story;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.plan.Plan;
import dto.story.Comment;
import dto.story.Story;
import service.plan.PlanService;
import service.plan.PlanServiceImpl;
import service.stroy.StoryService;
import service.stroy.StoryServiceImpl;

@WebServlet("/story/comment/write")
public class StoryCommentWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PlanService pService = new PlanServiceImpl();
	StoryService sService = new StoryServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		Comment comment = new Comment();
		Gson gson = new Gson();
		
		String commJson = req.getParameter("commJson");
		
		// plan_idx Set
	    if(commJson!=null & !"".equals(commJson)){
	    	comment = gson.fromJson(commJson, Comment.class);
	    } else { // 테스트용 코드, 테스트 후에는 삭제
	      System.out.println("Comment가 null 혹은 빈값"); 
	    }
	    
	    int userIdx =  (int) req.getSession().getAttribute("user_idx");
	   
	    comment.setUser_idx(userIdx);
	    
	    sService.writeComment(comment);
	    
	
		//-----
		
		Plan plan = new Plan();
		
		plan.setPlan_idx(comment.getPlan_idx());
		
		List<Comment> CommentList = sService.getCommentList(plan);
		
		req.setAttribute("commentList", CommentList);
		req.setAttribute("story_idx", comment.getStory_idx());
		req.getRequestDispatcher("/plan/story/storyCommentView.jsp").forward(req, resp);
	    
	}
}

