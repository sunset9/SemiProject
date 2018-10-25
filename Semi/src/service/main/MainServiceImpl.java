package service.main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.main.MainDao;
import dao.main.MainDaoImpl;
import dto.plan.Plan;
import dto.user.User;

public class MainServiceImpl implements MainService {

	MainDao mainDao = new MainDaoImpl();
	
	//추천 콘텐츠 리스트 가져오기 
	@Override
	public List getRecommendedContents() {
		
		return mainDao.getRecommendedContents();
	}

	//최신콘텐츠 리스트 가져오기 
	@Override
	public List getNewestContents() {
		
		return mainDao.getNewestContents();
	}

	//새 일정 정보 파라미터로 받아오기
	@Override
	public Plan getNewPlanParam(HttpServletRequest req, HttpServletResponse resp) {
		Plan plan = new Plan();
		
		plan.setTitle(req.getParameter("title"));
		
		//setStart_date, setEnd_date 해주기
		String sD = req.getParameter("startDate");
		String eD = req.getParameter("endDate");
		//System.out.println("파라미터 확인 "+sD);
		//System.out.println("파라미터 확인 "+eD);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date startDate = null;
		Date endDate = null;
		
		try {
			startDate = sdf.parse(sD);
			endDate = sdf.parse(eD);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println("이거확인 "+startDate);
		//System.out.println("이거확인 "+endDate);
		
		plan.setStart_date(startDate);
		plan.setEnd_date(endDate);
		
		plan.setTraveled(Integer.parseInt(req.getParameter("traveled")));
		
		return plan;
	}

	//새 일정 만들기 
	@Override
	public void createPlan(Plan param, User user) {
		mainDao.insertPlan(param, user);
	}

	//plan_idx 가져오기 
	@Override
	public int getPlan_idx() {
		return mainDao.getPlan_idx();
	}

	


	

}
