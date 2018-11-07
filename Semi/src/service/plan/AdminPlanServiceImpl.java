package service.plan;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.account.AccountDao;
import dao.account.AccountDaoImpl;
import dao.plan.PlanDao;
import dao.plan.PlanDaoImpl;
import dao.timetable.TimetableDao;
import dao.timetable.TimetableDaoImpl;
import dto.Account.Account;
import dto.plan.Plan;
import dto.user.User;
import utils.Paging;

public class AdminPlanServiceImpl implements AdminPlanService{
	AccountDao accDao = new AccountDaoImpl();
	PlanDao planDao = new PlanDaoImpl();
	TimetableDao ttDao = new TimetableDaoImpl();
	
	@Override
	public Plan getParam(HttpServletRequest req) {
		Plan plan = new Plan();
		plan.setPlan_idx(Integer.parseInt(req.getParameter("plan_idx")));
		
		return plan;
	}
	@Override
	public boolean delete(Plan plan) {
		ttDao.deleteTimetableListByPlanIdx(plan);
		return planDao.deletePlanByPlanIdx(plan);
		}
	@Override
	
	public List<Plan> selectPlanAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Plan> selectPlanByTitle(Plan plan) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//---------------------------------------------------
	
	
	@Override
	public boolean loginCheck(HttpServletRequest req) {
		boolean check =false;
		
		if(req.getSession().getAttribute("login")!=null){
			check = (boolean)req.getSession().getAttribute("login");
		}
		
//		System.out.println("check : " +check);
		return check;
	}
	
	@Override
	public int getCurPage(HttpServletRequest req) {
		// 요청 파라미터 받아오기
		String curPage = req.getParameter("curPage");
				
		// null이나 공백이 아닐 때 변수값 반환하기
		if( curPage != null && !"".equals(curPage)) {
			return Integer.parseInt(curPage);
		}
					
		// null이나 공백일 때 0 반환
		return 0;
	}
	
	@Override
	public String getSearch(HttpServletRequest req) {
		String search = req.getParameter("search");
		return search;
	}
	
	@Override
	public int getTotalCount(int searchType, String search ) {
		return planDao.selectCntAll( searchType,search);
	}
	
	@Override
	public List<Plan> getAllPagingList(Paging paging) {
		return planDao.selectAllPagingList(paging);
	}
	
	

}
