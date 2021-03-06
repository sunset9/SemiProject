package controller.story;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Account.AccType;
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

@WebServlet("/story/write")
public class StoryWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PlanService pService = new PlanServiceImpl();
	StoryService sService = new StoryServiceImpl();
	TimetableService ttbService = new TimetableServiceImpl();
	AccountService aService = new AccountServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
	    StoryService sService = new StoryServiceImpl();
		
		Story story = new Story();
		
		story = sService.getParam(req);
		
		int storyidx = sService.write(story);
		
		req.setAttribute("plan_idx", story.getPlan_idx());
		
		//환율정보가져오기
		double USD_rate = Double.parseDouble(req.getParameter("USD_rate"));
		double KRW_rate = Double.parseDouble(req.getParameter("KRW_rate"));
		double JPY_rate = Double.parseDouble(req.getParameter("JPY_rate"));
		
		//가계부정보가져오기
		String[] accType = req.getParameterValues("accType");
		String[] currSymbol = req.getParameterValues("currSymbol");
		String[] cost = req.getParameterValues("cost");

		
		for (int i =0 ;i<accType.length;i++) {
			
			if ((cost[i] != null && cost[i] != "")
				&& (currSymbol[i] != null && currSymbol[i] != null) 
				&&  (accType[i] != null && accType[i] != null)) {
				
				Account account= new Account();
				
				account.setAccType(AccType.getValue(Integer.parseInt(accType[i])));
				account.setCurr_idx(Integer.parseInt(currSymbol[i]));
				//cost 콤마제거
				cost[i]=cost[i].replaceAll(",", "");
				account.setOrigin_cost(Double.parseDouble(cost[i]));
				account.setPlan_idx(story.getPlan_idx());
				account.setStory_idx(storyidx);
				account.setCaled_cost(
						aService.calcCost(account.getCurr_idx(), account.getOrigin_cost(), USD_rate, KRW_rate, JPY_rate)
						);
				
				aService.Write(account);
			}
		}
		
		
		//스토리다시뿌려주기
		
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

