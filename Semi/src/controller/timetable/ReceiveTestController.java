package controller.timetable;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dto.timetable.Location;
import dto.timetable.Timetable;


@WebServlet("/timetable/recv")
public class ReceiveTestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		// -------------- jsp 에서 event리스트 받아오기 테스트 ---------
		 GsonBuilder gsonBuilder = new GsonBuilder();
         gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm");
         Gson gson = gsonBuilder.create();
		
		// --- events로 json형식으로 여러개 넘겨주는 경우
//		String[] events = req.getParameterValues("events");
//		for(String event:events) {
//			Location loc = gson.fromJson(event, Location);
//			System.out.println(loc);
//		}
		
		// --- events로 리스트형식으로 하나 넘겨주는 경우
		String events = req.getParameter("events");
		// test-log
		System.out.println(events);
		
		Location[] objs = gson.fromJson(events, Location[].class);
		List<Location> list = Arrays.asList(objs);

		// test-log
		for(Location loc:list) {
			System.out.println(loc);
		}
	
		//-------------------- DB에 있는정보 뿌려주기-----------------------------
		List<Timetable> ttbList = new ArrayList<Timetable>() ;
		List<Location> locList = new ArrayList<Location>();
		for(int i = 0 ; i<list.size(); i++) {
			Timetable ttb = new Timetable();
			ttb.setStart_time(objs[i].getStart_time());
			ttb.setEnd_time(objs[i].getEnd_time());
			ttb.setLoc_idx(1);
			
			Location loc = new Location();
			loc.setLoc_idx(1);
			loc.setAddress(objs[i].getAddress());
			loc.setPlace_name(objs[i].getPlace_name());
			
			ttbList.add(ttb);
			locList.add(loc);
		}
		
		
		String ttbListStr = gson.toJson(ttbList);
		String locListStr = gson.toJson(locList);
		
		// test-log
		System.out.println(ttbListStr);
		System.out.println(locListStr);
		
		req.setAttribute("ttbList", ttbListStr);
		req.setAttribute("locList", locListStr);
		
		req.getRequestDispatcher("/plan/timetable/timetable_recv.jsp").forward(req, resp);
		
	}
}
