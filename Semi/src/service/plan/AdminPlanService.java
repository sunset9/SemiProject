package service.plan;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.plan.PlanDao;
import dto.Account.Account;
import dto.plan.Plan;
import utils.Paging;

public interface AdminPlanService {

	// 파라미터 얻어오기 
	public Plan getParam(HttpServletRequest req);
	
	// 일정 삭제
	public void delete(Plan plan);
	
	// 일정 전체 조회 
	public List<Plan> selectPlanAll();
	
	// 일정 제목으로 조회하기
	public List<Plan> selectPlanByTitle(Plan plan);
	
	// 로그인 체그
	public boolean loginCheck(HttpServletRequest req);
	
	// 현재 페이지 얻어오기 
	public int getCurPage(HttpServletRequest req);
	
	// 검색어 얻어오기
	public String getSearch(HttpServletRequest req);
	
	// 전체 페이지 얻어오기 
	public int getTotalCount(int searchType, String search );
	
	// 게시글 리스트 조회 
	public List<Plan> getPagingList(Paging paging);
	
	
}
