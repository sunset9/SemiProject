package service.plan;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.plan.PlanDao;
import dto.Account.Account;
import dto.plan.Plan;

public interface AdminPlanService {

	// 파라미터 얻어오기 
	public Plan getParam(HttpServletRequest req);
	
	// 일정 삭제
	public void delete(Plan plan);
	
	// 일정 전체 조회 
	public List<Plan> selectPlanAll();
	
	// 일정 제목으로 조회하기
	public List<Plan> selectPlanByTitle(Plan plan);
	

}
