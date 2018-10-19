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

import service.stroy.StoryService;
import service.stroy.StoryServiceImpl;
import utils.CalcDate;
import dto.plan.Plan;
import dto.story.Story;

/**
 * Servlet implementation class StoryViewController
 */
@WebServlet("/story/view")
public class StoryViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		StoryService sService = new StoryServiceImpl();
		
		List<Story> StoryList = new ArrayList<>();
	
		// jsp에서 planidx 파라미터값 가져오기
//		String plan_idx = request.getParameter("planidx");

		Plan plan = new Plan();
		
//		plan.setPlan_idx(Integer.parseInt(plan_idx));
		
		// 플렌번호를 얻어와서 저장
		try {
			plan.setStart_date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-10"));
			plan.setEnd_date(new SimpleDateFormat("yyyy-MM-dd").parse("2018-10-13"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		plan.setPlan_idx(1);
		
		// 플랜번호로 총 스토리 조회
		StoryList=sService.getStoryList(plan);
		
		//날짜계산 유틸
		CalcDate calcDate = new CalcDate();
		
		// 여행 시작, 여행 끝 기간계산 
		int diffDays = calcDate.CalcPriod(plan);
		
		request.setAttribute("diffDays",diffDays);
		request.setAttribute("plan", plan);
		request.setAttribute("storyList", StoryList);
		
		request.getRequestDispatcher("/plan/story/storyView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
