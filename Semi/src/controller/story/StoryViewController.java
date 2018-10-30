package controller.story;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.account.AccountService;
import service.account.AccountServiceImpl;
import service.plan.PlanService;
import service.plan.PlanServiceImpl;
import service.stroy.StoryService;
import service.stroy.StoryServiceImpl;
import service.timetable.TimetableService;
import service.timetable.TimetableServiceImpl;
import service.user.UserService;
import service.user.UserServiceImpl;
import utils.CalcDate;
import dto.Account.Account;
import dto.plan.Plan;
import dto.story.Comment;
import dto.story.Story;
import dto.timetable.Timetable;
import dto.user.User;

/**
 * Servlet implementation class StoryViewController
 */
@WebServlet("/story/view")
public class StoryViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	TimetableService ttbService = new TimetableServiceImpl();
	StoryService sService = new StoryServiceImpl();
	PlanService pService = new PlanServiceImpl();
	UserService uService = new UserServiceImpl();
	AccountService aService = new AccountServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		List<Story> StoryList = new ArrayList<>();
	
		Plan plan = new Plan();
		
		String plan_idx = request.getParameter("plan_idx");
		
	    if(plan_idx!=null & !"".equals(plan_idx)){
	    	//스토리 읽어올때
	    	plan.setPlan_idx(Integer.parseInt(plan_idx));
	    	
	      } else {
	    	  // 스토리 저장하고 난후 ajax로 여기다시 불러올떄
	    	  int plan_idx_write = (int) (request.getAttribute("plan_idx"));
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
		
		
		List<Account> accountList = aService.getPlanAccountList(plan);
		
		
		
		request.setAttribute("accountList",accountList );

		request.setAttribute("ttbList", ttbList);
		request.setAttribute("diffDays",diffDays);
		request.setAttribute("storyList", StoryList);
		
		request.getRequestDispatcher("/plan/story/storyView.jsp").forward(request, response);
		
	}


}
