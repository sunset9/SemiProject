package controller.timetable;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dto.plan.Plan;
import dto.timetable.Location;
import dto.timetable.Timetable;
import service.timetable.TimetableService;
import service.timetable.TimetableServiceImpl;

@WebServlet("/view/ttb")
public class ViewTestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	TimetableService ttbService = new TimetableServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm");
        Gson gson = gsonBuilder.create();
		
		// 1번 플랜으로 테스트
		Plan plan = new Plan();
		plan.setPlan_idx(1);
		
		// timetable, location 리스트 받기
		List<Timetable> ttbList = ttbService.getTimetableList(plan);
		List<Location> locList = ttbService.getLocationList(plan);
		
		// timetable 과 location이 1:1 대응하지 않는 경우 (DB데이터 문제)
		if(ttbList.size() != locList.size()) {
			System.out.println("[ERR] 타임테이블과 위치정보의 개수가 일치하지 않습니다.");
			return;
		}
		
		System.out.println(ttbList);
		System.out.println(locList);
		
		// JSON 형태로 변환
		String ttbListStr = gson.toJson(ttbList);
		String locListStr = gson.toJson(locList);
		
		System.out.println(ttbListStr);
		System.out.println(locListStr);
		
		// 파라미터 지정
		req.setAttribute("ttbList", ttbListStr);
		req.setAttribute("locList", locListStr);
		req.setAttribute("startDate", "2018-04-17");
		req.setAttribute("endDate", "2018-04-22");
//		req.getRequestDispatcher("/plan/timetable/timetable_recv.jsp").forward(req, resp);
		req.getRequestDispatcher("/plan/timetable/timetable_recv_map.jsp").forward(req, resp);
	}
}
