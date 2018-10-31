package dao.plan;

import java.util.List;

import dto.Account.Account;
import dto.plan.Plan;
import dto.user.User;
import utils.Paging;

public interface PlanDao {

	// 일정메인 인덱스로 일정 정보 불러오기
	Plan selectPlanInfoByPlanIdx(int plan_idx);
	
	// 일정메인 인덱스로 일정 정보 불러오기(파라미터 타입이 Plan)
	Plan selectPlanInfoByPlanIdx(Plan plan);
	
	// 유저 아이디로 유저 정보 불러오기 -> 게시자 정보
	User selectUserInfoByUserIdx(Plan plan);
	
	// 유저 아이디로 유저 정보 불러오기 -> 게시자 정보
	User selectUserInfoByUserIdx(User user);
		
	// 유저의 전체 게시글 수 가져오기
	int selectPlanCntAll();
	
	// 유저의 전체 게시글의 총 거리 계산하기 
	int selectTotalDistance();
		
	// 플랜의 가계부 인덱스로 가계부 정보 불러오기
	Account selectAccountInfoByAccountIdx(Plan plan);
	
	// 일정메인 수정값 저장하기
	void update(Plan plan);
	
	// 일정 삭제하기
	void deletePlanByPlanIdx(Plan plan);
	
	// 전체 일정 불러오기 
	List<Plan> selectPlanAllList();
	
	//제목으로 일정 검색 하기 
	Plan selectPlanTitle(Plan plan);
	
	//새 일정 만들기 파라미터 저장
	public void insertPlan(Plan param, User user);
	
	//plan_idx 가져오기
	public int getPlan_idx();

	//planner_seq.nextval 가져오기
	public int getPlannerSeqNextval();
	
	//카테고리별 총 가격
	int sumAirfareByPlanIdx(Plan plan);
	int sumTrafficByPlanIdx(Plan plan);
	int sumStayByPlanIdx(Plan plan);
	int sumAdmissionByPlanIdx(Plan plan);
	int sumFoodByPlanIdx(Plan plan);
	int sumPlayByPlanIdx(Plan plan);
	int sumShopByPlanIdx(Plan plan);
	int sumEtcByPlanIdx(Plan plan);
	
	//리스트 검색하기
	public List<Plan> selectList(String category, String searchValue);
	
	// 전체 게시물 수 조회
	public int selectCntAll( int searchType, String search) ;

	// 페이징 된 plan 리스트 조회
	public List<Plan> selectPagingList(Paging paging);

}
