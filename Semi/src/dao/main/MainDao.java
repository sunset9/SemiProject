package dao.main;

import java.util.List;

import dto.plan.Plan;
import dto.user.User;

public interface MainDao {

	//추천콘텐츠 검색
	public List getRecommendedContents();
	
	//최신콘텐츠 검색
	public List getNewestContents();
	
	//새 일정 만들기
	public void insertPlan(Plan param, User user_idx);
	
	//plan_idx 가져오기
	public int getPlan_idx();
}
