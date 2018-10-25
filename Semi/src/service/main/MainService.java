package service.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.plan.Plan;
import dto.user.User;

public interface MainService {

	//추천콘텐츠 리스트 가져오기
	public List getRecommendedContents();
	
	//최신콘텐츠 리스트 가져오기
	public List getNewestContents();
	
	//새 일정 정보(요청 파라미터) 가져오기
	public Plan getNewPlanParam(HttpServletRequest req, HttpServletResponse resp);
	
	//새 일정 만들기 
	public void createPlan(Plan param, User user_idx);
	
	//plan_idx 가져오기 
	public int getPlan_idx();
}
