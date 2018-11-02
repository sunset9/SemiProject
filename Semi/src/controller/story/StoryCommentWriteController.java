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
		
	    if(commJson!=null & !"".equals(commJson)){
	    	comment = gson.fromJson(commJson, Comment.class);
	    } else { 
	      System.out.println("Comment가 null 혹은 빈값"); 
	    }
	    
	    int userIdx =  (int) req.getSession().getAttribute("user_idx");
	   
	    comment.setUser_idx(userIdx);
	    
	    //커멘트 추가
	    sService.writeComment(comment);
	    
	
		//커멘트 뿌려주기 위한 동작
		
		Plan plan = new Plan();
		
		plan.setPlan_idx(comment.getPlan_idx());
		
		List<Comment> CommentList = sService.getCommentList(plan);
		
		//커멘트 리스트 값 전달하기
		//스토리idx값 전달하기
		req.setAttribute("commentList", CommentList);
		req.setAttribute("story_idx", comment.getStory_idx());
		req.getRequestDispatcher("/plan/story/storyCommentView.jsp").forward(req, resp);
	    
	}
}

