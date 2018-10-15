package service.plan;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.account.AccountDao;
import dao.account.AccountDaoImpl;
import dao.plan.PlanDao;
import dto.Account.Account;
import dto.plan.Plan;
import dto.user.User;

public interface PlanService {

	// 왼쪽에 띄워줄 유저 정보 가져오기
	User getUserInfo(Plan plan);
	
	// 특정 유저의 일정 목록 가져오기
	List<Plan> getPlanList(User userinfo);
	
	// 특정 유저의 북마크 목록 가져오기
	List<Plan> getBookmarkList(User userinfo);

	// 요청파라미터(plan_idx) -> Plan 모델 
	Plan getParam(HttpServletRequest req);

	// 일정 기본 정보 가져오기
	Plan getPlanInfo(Plan plan);

	// 가계부 정보 가져오기
	Account getAccount(Plan plan);

	// 일정 삭제
	void delete(Plan plan);

	// 일정 저장 (새로운 일정)
	void write(Plan plan);
	
	// 수정된 일정 저장
	void update(Plan plan);

}
