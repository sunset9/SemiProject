package controller.story;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.story.Story;
import service.plan.PlanService;
import service.plan.PlanServiceImpl;
import service.stroy.StoryService;
import service.stroy.StoryServiceImpl;

@WebServlet("/story/delete")
public class StoryDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PlanService pService = new PlanServiceImpl();
	StoryService sService = new StoryServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
		    StoryService sService = new StoryServiceImpl();
			
			Story story = new Story();
			
			String story_idx = req.getParameter("story_Idx");
			
			if(story_idx!=null & !"".equals(story_idx)){
		    	//스토리 읽어올때
		    	story.setStory_idx(Integer.parseInt(story_idx));
		    	
		      } else {
		    	  // 스토리 저장하고 난후 ajax로 여기다시 불러올떄
		    	  System.out.println("스토리 idx가 잘못됨");
		      }
			
			sService.delete(story);
			
			
			String plan_idx = req.getParameter("plan_idx");
			
			if(plan_idx!=null & !"".equals(plan_idx)){
		    	//스토리 읽어올때
		    	req.setAttribute("plan_idx", Integer.parseInt(plan_idx));
		      } else {
		    	  // 스토리 저장하고 난후 ajax로 여기다시 불러올떄
		    	  System.out.println("plan idx가 잘못됨");
		      }
			
//			req.getRequestDispatcher("/story/view").forward(req, resp);
//			위에껄로하면 자꾸 405 오류남
			
			StoryViewController SV = new StoryViewController();
			SV.doGet(req, resp);
		
		
	}
}

