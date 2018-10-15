package service.plan;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.account.AccountDao;
import dao.account.AccountDaoImpl;
import dao.plan.PlanDao;
import dao.plan.PlanDaoImpl;
import dto.Account.Account;
import dto.plan.Plan;
import dto.user.User;

public class PlanServiceImpl implements PlanService{
	AccountDao accDao = new AccountDaoImpl();
	PlanDao plandao = new PlanDaoImpl();
	
	//왼쪽에 띄워줄 유저 정보 가져오기
	public User getUserInfo(Plan plan) {
		return plandao.getUserInfoByUserIdx(plan);

	}

	// 특정 유저의 일정 목록 가져오기
	public List<Plan> getPlanList(User userinfo) {return null;	}

	// 특정 유저의 북마크 목록 가져오기
	public List<Plan> getBookmarkList(User userinfo) {return null;	}

	// 요청파라미터(plan_idx) -> Plan 모델 
	public Plan getParam(HttpServletRequest req) {
		String param = req.getParameter("plan_idx");
		Plan plan = new Plan();
		return plan;
	}

	// 일정 기본 정보 가져오기
	public Plan getPlanInfo(Plan plan) {
		
//		일정 정보 불러오기(제목, 거리 등)
		return plandao.getPlanInfoByPlanIdx(plan);
	}

	// 가계부 정보 가져오기
	public Account getAccount(Plan plan){
		return plandao.getAccountInfoByAccountIdx(plan);
	}

	// 일정 삭제
	public void delete(Plan plan) {
		// 일정 삭제
		plandao.delete(plan);
	}

	// 일정 저장 (새로운 일정)
	public void write(Plan plan) {
		plandao.insert();
		
	}
	
	// 수정된 일정 저장
	public void update(Plan plan) {
		plandao.update(plan);
	}


}
