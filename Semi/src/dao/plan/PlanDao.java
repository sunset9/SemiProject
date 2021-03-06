package dao.plan;

import java.util.List;

import dto.Account.AccType;
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
	int selectPlanCntAll(int user);
	
	// 플랜의 가계부 인덱스로 가계부 정보 불러오기
	Account selectAccountInfoByAccountIdx(Plan plan);
	
	// 일정메인 수정값 저장하기
	void update(Plan plan);
	
	// 일정 삭제하기
	boolean deletePlanByPlanIdx(Plan plan);
	
	// 전체 일정 불러오기 
	List<Plan> selectPlanAllList();
	
	//제목으로 일정 검색 하기 
	Plan selectPlanTitle(Plan plan);
	
	//새 일정 만들기 파라미터 저장
	public void insertPlan(Plan param, User user);
	
	//plan_idx 가져오기
	public int getPlan_idx(User user);

	//planner_seq.nextval 가져오기
	public int getPlannerSeqNextval();
	
	//카테고리별 총 가격
	long sumAccTypeCost(Plan plan, AccType accType);
	
	// 전체 게시물 수 조회
	public int selectCntAll( int searchType, String search) ;
	
	// 최신 게시물 수 조회
	public int selectNewCntAll( int searchType, String search) ;
	
	// 추천 게시물 수 조회
	public int selectRecomCntAll( int searchType, String search) ;

	// 페이징 된 plan 리스트 조회
	public List<Plan> selectPagingList(Paging paging);
	
	// 최신 게시물 페이징 된 plan 리스트 조회
	public List<Plan> selectNewPagingList(Paging paging);
	
	// 추천 게시물 페이징 된 plan 리스트 조회
	public List<Plan> selectRecomPagingList(Paging paging);

	//배너 업데이트 !
	public void bannerUpdate(Plan plan);
	
	// 최신 게시물 리스트 조회 
	public List<Plan> selectNewList();
	
	// 추천 게시물 리스트 조회
	public List<Plan> selectRecomList();
	
	// 비공개글 포함한 전체 게시물 수 조회
	public int selectAllCntAll( int searchType, String search) ;
	
	// 비공개 개시물 포함한 게시물 리스트 조회 -> 관리자가 사용 
	public List<Plan> selectAllPagingList(Paging paging);
	
	String[] rownumCountryName(Plan plan);
}
