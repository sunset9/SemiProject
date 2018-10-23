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
	
	// 요청파라미터(plan_idx) -> Plan 모델
	@Override
	public Plan getParam(HttpServletRequest req) {
		//요청파라미터 정보를 저장할 DTO객체
		Plan plan = new Plan();
		
		//요청파라미터 받기
		String param = req.getParameter("plan_idx");
		
		//null이나 ""이 아니면 int로 변환하여 DTO에 저장
		if( param != null && !"".equals(param) ) {
			plan.setPlan_idx(Integer.parseInt(param));
		}

		//요청파라미터가 객체로 변환된 DTO 반환
		return plan;
	}
	
	// 일정 기본 정보 가져오기
	@Override
	public Plan getPlanInfo(Plan plan) {
		//조회수 증가
		//plandao.updateHit(plan);
		
		//일정 정보 불러오기(제목, 거리 등)
		return plandao.selectPlanInfoByPlanIdx(plan);
	}
	
	//왼쪽에 띄워줄 유저 정보 가져오기
	@Override
	public User getUserInfo(Plan plan) {
		return plandao.selectUserInfoByUserIdx(plan);
	}
	
	// 가계부 정보 가져오기
	@Override
	public Account getAccount(Plan plan){
		return plandao.selectAccountInfoByAccountIdx(plan);
	}

	// 특정 유저의 일정 목록 가져오기
	@Override
	public List<Plan> getPlanList(User userinfo) {return null;	}

	// 특정 유저의 북마크 목록 가져오기
	@Override
	public List<Plan> getBookmarkList(User userinfo) {return null;	}
	
	// 일정 삭제
	@Override
	public void delete(Plan plan) {
		// 일정 삭제
		plandao.delete(plan);
	}

	// 일정 저장 (새로운 일정)
	@Override
	public void write(Plan plan) {
		plandao.insert();
	}
	
	// 수정된 일정 저장
	@Override
	public void update(Plan plan) {
		plandao.update(plan);
	}
}
