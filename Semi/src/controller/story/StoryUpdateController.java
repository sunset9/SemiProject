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

/**
 * Servlet implementation class StoryUpdateController
 */
@WebServlet("/story/update")
public class StoryUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	PlanService pService = new PlanServiceImpl();
	StoryService sService = new StoryServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("utf-8");
		
		   StoryService sService = new StoryServiceImpl();
			
			Story story = new Story();
			
			story = sService.getParam(req);
			
			sService.update(story);
			
			req.setAttribute("plan_idx", story.getPlan_idx());
			
//			req.getRequestDispatcher("/story/view").forward(req, resp);
//			위에껄로하면 자꾸 405 오류남
			
			StoryViewController SV = new StoryViewController();
			SV.doGet(req, resp);
	
	
	}
}


