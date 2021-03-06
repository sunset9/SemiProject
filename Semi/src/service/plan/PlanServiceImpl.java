package service.plan;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;

import dao.account.AccountDao;
import dao.account.AccountDaoImpl;
import dao.plan.PlanDao;
import dao.plan.PlanDaoImpl;
import dto.Account.AccType;
import dto.Account.Account;
import dto.plan.Plan;
import dto.user.User;

public class PlanServiceImpl implements PlanService{
	AccountDao accDao = new AccountDaoImpl();
	PlanDao plandao = new PlanDaoImpl();
	
	// 요청파라미터(plan_idx) -> Plan 모델
	@Override
	public Plan getSessionPlan(HttpServletRequest req) {
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
	public User getSessionUser(HttpServletRequest req) {
		//요청파라미터 정보를 저장할 DTO객체
		User user = new User();
		
		int param = -1;
		//요청파라미터 받기
//		로그인인지 아닌지 확인, 로그인이 아니면 user_idx = 0
		if( req.getSession().getAttribute("user_idx") != null) {
			param = (int)req.getSession().getAttribute("user_idx");
		}
		
		user.setUser_idx(param);
//			//null이나 ""이 아니면 int로 변환하여 DTO에 저장
//			if( param != null && !"".equals(param) ) {
//				plan.setPlan_idx(Integer.parseInt(param));
//			}
		
		//요청파라미터가 객체로 변환된 DTO 반환
		return user;
	}
	
	@Override
	public Plan getParam(HttpServletRequest req) {
		//요청파라미터 정보를 저장할 DTO객체
		Plan plan = new Plan();
		
		System.out.println("planServiceImpl getParameter : "+req.getParameter("plan_idx"));
		
		//요청파라미터 받기
		int param = Integer.parseInt(req.getParameter("plan_idx"));
		plan.setPlan_idx(param);
//		//null이나 ""이 아니면 int로 변환하여 DTO에 저장
//		if( param != null && !"".equals(param) ) {
//			plan.setPlan_idx(Integer.parseInt(param));
//		}
		
		//요청파라미터가 객체로 변환된 DTO 반환
		return plan;
	}
	
	@Override
	public Plan getParamEdit(MultipartRequest mul) {
		Plan plan = new Plan();
		Date dateStart = new Date();
		Date dateEnd = new Date();
		
		int plan_idx = Integer.parseInt(mul.getParameter("plan_idx"));
		int user_idx = Integer.parseInt(mul.getParameter("user_idx"));
		plan.setPlan_idx(plan_idx);
		plan.setUser_idx(user_idx);
		plan.setTitle(mul.getParameter("editTitleView"));
		
		try {
			dateStart = new SimpleDateFormat("yyyy-MM-dd").parse(mul.getParameter("editStartDate"));
			dateEnd = new SimpleDateFormat("yyyy-MM-dd").parse(mul.getParameter("editEndDate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		plan.setStart_date(dateStart);
		plan.setEnd_date(dateEnd);
		
		//1 : 여행전, 0 : 여행후
		plan.setTraveled(Integer.parseInt(mul.getParameter("editTraveled")));
		
		if( mul.getParameter("editOpened").equals("1") && mul.getParameter("editOpened") != null) {
			plan.setOpened(Integer.parseInt(mul.getParameter("editOpened")));
		} else {
			plan.setOpened(0);
		}
		
		return plan;
	}
	
	// 요청파라미터 처리(main 새일정만들기에서 넘어온 파라미터)
		@Override
		public Plan getParamCreate(HttpServletRequest req) {
			Plan plan = new Plan();
			Date dateStart = new Date();
			Date dateEnd = new Date();

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
			plan.setTraveled(Integer.parseInt(req.getParameter("editTraveled")));
			plan.setOpened(Integer.parseInt(req.getParameter("editOpened")));

			return plan;
		}
		
	// 일정 기본 정보 가져오기
	@Override
	public Plan getPlanInfo(int plan_idx) {
		
		//일정 정보 불러오기(제목 등)
		return plandao.selectPlanInfoByPlanIdx(plan_idx);
	}
	
	// 일정 기본 정보 가져오기
	@Override
	public Plan getPlanInfo(Plan plan) {
		// TODO Auto-generated method stub
		return plandao.selectPlanInfoByPlanIdx(plan);
	}
	
	//왼쪽에 띄워줄 유저 정보 가져오기
	@Override
	public User getUserInfo(Plan plan) {
		User user = plandao.selectUserInfoByUserIdx(plan);
		
		return user;
	}
	
	//로그인 유저 정보 가져오기
	@Override
	public User getUserInfoLogin(User user) {
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
	
	@Override
	public void deletePlan(Plan plan) {
		// 일정 삭제
		plandao.deletePlanByPlanIdx(plan);
	}
	
	// 수정된 일정 저장
	@Override
	public void update(Plan plan) {
		plandao.update(plan);
	}
	
	//새 일정 만들기 
	@Override
	public void createPlan(Plan param, User user) {
		plandao.insertPlan(param, user);
	}

	//plan_idx 가져오기 
	@Override
	public int getPlan_idx(User cUser) {
		return plandao.getPlan_idx(cUser);
	}

	// 카테고리 별 총합
	@Override
	public long getAccountAccTpeCost(Plan plan, AccType accType) {
		return plandao.sumAccTypeCost(plan, accType);
	}

	// 한 일정의 가계부 비용 총합
	@Override
	public long getAccountTotal(EnumMap<AccType, Long> accEnumMap) {
		long total = 0;
		for(long cost : accEnumMap.values()) {
			total += cost;
		}
		return total;
	}
	
	@Override
	public String[] getCountryName(Plan plan) {
		return plandao.rownumCountryName(plan);
	}

	@Override
	public void updateBanner(MultipartRequest mul, Plan planView) {
		//배너 업데이트
		if(mul.getParameter("bannerURL") != null && !"".equals((String)mul.getParameter("bannerURL"))) {
			String path = (String) mul.getParameter("bannerURL");
			planView.setBannerURL(path);
			// DB에서 유저의 banner 수정 
			plandao.bannerUpdate(planView);
		}
		
	}
}
