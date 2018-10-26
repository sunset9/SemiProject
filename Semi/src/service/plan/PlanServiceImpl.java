package service.plan;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	public Plan getSession4Plan(HttpServletRequest req) {
		//요청파라미터 정보를 저장할 DTO객체
		Plan plan = new Plan();
		
		//요청파라미터 받기
		int param = (int)req.getSession().getAttribute("plan_idx");
		plan.setPlan_idx(param);
//		//null이나 ""이 아니면 int로 변환하여 DTO에 저장
//		if( param != null && !"".equals(param) ) {
//			plan.setPlan_idx(Integer.parseInt(param));
//		}
		
		//요청파라미터가 객체로 변환된 DTO 반환
		return plan;
	}
	
	// 요청파라미터(plan_idx) -> Plan 모델
	@Override
	public User getSession4User(HttpServletRequest req) {
		//요청파라미터 정보를 저장할 DTO객체
		User user = new User();
		
		//요청파라미터 받기
		int param = (int)req.getSession().getAttribute("user_idx");
		user.setUser_idx(param);
//			//null이나 ""이 아니면 int로 변환하여 DTO에 저장
//			if( param != null && !"".equals(param) ) {
//				plan.setPlan_idx(Integer.parseInt(param));
//			}
		
		//요청파라미터가 객체로 변환된 DTO 반환
		return user;
	}
	
	@Override
	public Plan getParam4Edit(HttpServletRequest req) {
		Plan plan = new Plan();
		Date dateStart = new Date();
		Date dateEnd = new Date();
		
		int plan_idx = (int)req.getSession().getAttribute("plan_idx");
		plan.setPlan_idx(plan_idx);
		
//		plan.setUser_idx(1);
		
		plan.setTitle(req.getParameter("editTitleView"));
		
		try {
			dateStart = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("editStartDate"));
			dateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("editEndDate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		plan.setStart_date(dateStart);
		plan.setEnd_date(dateEnd);
		
		//1 : 여행전, 0 : 여행후
		plan.setTraveled(Integer.parseInt(req.getParameter("editTraveled")));
		System.out.println(req.getParameter("checkbox"));
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
	
	//로그인 유저 정보 가져오기
	@Override
	public User getUserInfo4Login(User user) {
		return plandao.selectUserInfoByUserIdx(user);
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
		plandao.insert(plan);
	}
	
	// 수정된 일정 저장
	@Override
	public void update(Plan plan) {
		plandao.update(plan);
	}

	
}
