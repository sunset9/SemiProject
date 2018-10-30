package controller.story;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.plan.Plan;
import dto.story.Story;
import dto.timetable.Timetable;
import service.plan.PlanService;
import service.plan.PlanServiceImpl;
import service.stroy.StoryService;
import service.stroy.StoryServiceImpl;
import service.timetable.TimetableService;
import service.timetable.TimetableServiceImpl;
import utils.CalcDate;

@WebServlet("/story/write")
public class StoryWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PlanService pService = new PlanServiceImpl();
	StoryService sService = new StoryServiceImpl();
	TimetableService ttbService = new TimetableServiceImpl();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		System.out.println("스토리라이트 컨트롤러");
		req.setCharacterEncoding("utf-8");
		
		String[] accType = req.getParameterValues("accType");
		String[] currSymbol = req.getParameterValues("currSymbol");
		String[] cost = req.getParameterValues("cost");

		for (int i =0 ;i<accType.length;i++) {
			System.out.println("accType::"+accType[i]);
			System.out.println("currSymbol::"+currSymbol[i]);
			System.out.println("cost::"+cost[i]);
		}
		
	    StoryService sService = new StoryServiceImpl();
		
		Story story = new Story();
		
		story = sService.getParam(req);
		
		sService.write(story);
		
		req.setAttribute("plan_idx", story.getPlan_idx());
		
		//--------------
		
		List<Story> StoryList = new ArrayList<>();
		
		Plan plan = new Plan();
		
		String plan_idx = req.getParameter("plan_idx");
		
	    if(plan_idx!=null & !"".equals(plan_idx)){
	    	//스토리 읽어올때
	    	plan.setPlan_idx(Integer.parseInt(plan_idx));
	    	
	      } else {
	    	  // 스토리 저장하고 난후 ajax로 여기다시 불러올떄
	    	  int plan_idx_write = (int) (req.getAttribute("plan_idx"));
	    	  if(plan_idx_write != 0) { 
	    		  plan.setPlan_idx(plan_idx_write);  
	    	  }else {
	    		  System.out.println("plan_idx 값이 잘못 되었습니다.");
	    	  }
	      }
	    
	    
	    plan = pService.getPlanInfo(plan);
	    
		
	    List<Timetable> ttbList = ttbService.getTimetableList(plan);
		
	    
		// 플랜번호로 스토리조회
		StoryList=sService.getStoryList(plan);
    
		//여행기간 계산 클래스 
		CalcDate calcDate = new CalcDate();
		
		// 여행기간 계산
		int diffDays = calcDate.CalcPriod(plan.getStart_date(),plan.getEnd_date());
		
		req.setAttribute("ttbList", ttbList);
		req.setAttribute("diffDays",diffDays);
		req.setAttribute("storyList", StoryList);
		
		req.getRequestDispatcher("/plan/story/storyView.jsp").forward(req, resp);
		
		
	}
}

