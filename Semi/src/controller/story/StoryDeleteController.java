package controller.story;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Account.Account;
import dto.plan.Plan;
import dto.story.Story;
import dto.timetable.Timetable;
import service.account.AccountService;
import service.account.AccountServiceImpl;
import service.plan.PlanService;
import service.plan.PlanServiceImpl;
import service.stroy.StoryService;
import service.stroy.StoryServiceImpl;
import service.timetable.TimetableService;
import service.timetable.TimetableServiceImpl;
import utils.CalcDate;

@WebServlet("/story/delete")
public class StoryDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PlanService pService = new PlanServiceImpl();
	StoryService sService = new StoryServiceImpl();
	AccountService aService = new AccountServiceImpl();
	TimetableService ttbService = new TimetableServiceImpl();
	
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
			
			//스토리 삭제, 스토리에딸린 가계부, 댓글 삭제
			sService.delete(story);
			sService.deleteCommentListByStoryIdx(story);
			aService.deleteAccountListByStoryIdx(story);
			
			
			
			//--스토리 다시 뿌려주기 위한 동작들
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
			
			List<Account> accountList = aService.getPlanAccountList(plan);
			
			req.setAttribute("accountList",accountList );
			req.getSession().getAttribute("user");
			req.setAttribute("ttbList", ttbList);
			req.setAttribute("diffDays",diffDays);
			req.setAttribute("storyList", StoryList);
			
			req.getRequestDispatcher("/plan/story/storyView.jsp").forward(req, resp);
		
		
	}
}

