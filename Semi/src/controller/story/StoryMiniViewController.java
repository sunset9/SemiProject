package controller.story;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dto.Account.Account;
import dto.story.Story;
import service.account.AccountService;
import service.account.AccountServiceImpl;
import service.plan.PlanService;
import service.plan.PlanServiceImpl;
import service.stroy.StoryService;
import service.stroy.StoryServiceImpl;

// 타임테이블에서 일정 누르면 뜨는 스토리 창
@WebServlet("/story/mini/view")
public class StoryMiniViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PlanService pService = new PlanServiceImpl();
	StoryService sService = new StoryServiceImpl();
	AccountService aService = new AccountServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/x-json; charset=UTF-8");
		
		Gson gson = new Gson();
		
		// 파라미터 추출
		Story param = sService.getParam(req);
		// 스토리 가져오기
		Story story = sService.getStory(param);
		
		//스토리 하나당 accountList 가져오는 부분
		List<Account> accountList = new ArrayList<Account>();
		accountList = aService.getStoryAccountList(story);
		
		
		// json 형식으로 변환
		String storyStr = gson.toJson(story);
		String accountListStr = gson.toJson(accountList);
		JsonObject obj = new JsonObject();
		obj.addProperty("story", storyStr);
		obj.addProperty("accountList", accountListStr);
		
		System.out.println(accountList);
		
		String jsonStr= gson.toJson(obj);
		
		resp.getWriter().println(jsonStr);
	}
	
}

