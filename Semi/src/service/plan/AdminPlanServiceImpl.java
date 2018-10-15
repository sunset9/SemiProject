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

public class AdminPlanServiceImpl implements AdminPlanService{
	AccountDao accDao = new AccountDaoImpl();
	PlanDao plandao = new PlanDaoImpl();
	@Override
	public void delete(Plan plan) {
		// TODO Auto-generated method stub
		
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
	@Override
	public Plan getParam(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
