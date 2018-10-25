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

@WebServlet("/story/write")
public class StoryWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PlanService pService = new PlanServiceImpl();
	StoryService sService = new StoryServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
	   StoryService sService = new StoryServiceImpl();
		
		Story story = new Story();
		
		story = sService.getParam(req);
		
		
		sService.write(story);
		
		
		resp.sendRedirect("/plan");
		
		
	}
}

