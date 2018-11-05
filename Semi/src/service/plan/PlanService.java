package service.plan;

import java.util.EnumMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Account.AccType;
import dto.Account.Account;
import dto.plan.Plan;
import dto.user.User;

public interface PlanService {

	// 게시자 유저 정보 가져오기
	User getUserInfo(Plan plan);
	
	// 로그인 유저 정보 가져오기
	User getUserInfoLogin(User user);
		
	// 특정 유저의 일정 목록 가져오기
	List<Plan> getPlanList(User userinfo);
	
	// 특정 유저의 북마크 목록 가져오기
	List<Plan> getBookmarkList(User userinfo);

	// 요청파라미터(plan_idx) -> Plan 모델 
	Plan getSessionPlan(HttpServletRequest req);
	
	// 요청파라미터(user_idx) -> Plan 모델 
		User getSessionUser(HttpServletRequest req);
		
	// 요청파라미터 처리(main 새일정만들기에서 넘어온 파라미터)
	Plan getParamCreate(HttpServletRequest req);
	
	Plan getParamEdit(HttpServletRequest req);
	
	Plan getParam(HttpServletRequest req);
		
	// 일정 기본 정보 가져오기
	Plan getPlanInfo(int plan_idx);
	
	// 일정 기본 정보 가져오기 (파라미터가 Plan타입)
	public Plan getPlanInfo(Plan plan);

	// 가계부 정보 가져오기
	Account getAccount(Plan plan);

	// 일정 삭제
	void deletePlan(Plan plan);
	
	// 수정된 일정 저장
	void update(Plan plan);
	
	//새 일정 만들기 
	public void createPlan(Plan param, User user_idx);

	//plan_idx 가져오기 
	public int getPlan_idx();
	
	//여행경로 2개 가져오기
	String[] getCountryName(Plan plan);
	
	//카테고리별 총 가격 가져오기
	int getAccountAccTpeCost(Plan plan, AccType accType);
	
	// 해당 플랜의 총 지출 비용
	int getAccountTotal(EnumMap<AccType, Integer> accEnumMap);
}
