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
import service.timetable.TimetableService;
import service.timetable.TimetableServiceImpl;
import utils.CalcDate;
import dto.plan.Plan;
import dto.story.Story;
import dto.timetable.Timetable;

/**
 * Servlet implementation class StoryViewController
 */
@WebServlet("/story/view")
public class StoryViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	TimetableService ttbService = new TimetableServiceImpl();
	StoryService sService = new StoryServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Story> StoryList = new ArrayList<>();
	
//		String plan_idx = request.getParameter("planidx");

		Plan plan = new Plan();
//		plan.setPlan_idx(Integer.parseInt(plan_idx));
		
		// 임시데이터 저장
		try {
			plan.setStart_date(new SimpleDateFormat("yyyyMMdd").parse("20181010"));
			plan.setEnd_date(new SimpleDateFormat("yyyyMMdd").parse("20181013"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		plan.setPlan_idx(1);
		
		
		List<Timetable> ttbList = ttbService.getTimetableList(plan);
		
		// 플랜번호로 스토리조회
		StoryList=sService.getStoryList(plan);
		System.out.println(StoryList);
		//여행기간 계산 클래스 
		CalcDate calcDate = new CalcDate();
		
		// 여행기간 계산로직
		int diffDays = calcDate.CalcPriod(plan.getStart_date(),plan.getEnd_date());

		request.setAttribute("ttbList", ttbList);
		request.setAttribute("diffDays",diffDays);
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
