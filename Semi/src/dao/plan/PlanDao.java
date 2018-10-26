package dao.plan;

import java.util.List;

import dto.Account.Account;
import dto.plan.Plan;
import dto.user.User;

public interface PlanDao {

	// 일정메인 인덱스로 일정 정보 불러오기
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
	
	// 새로운 일정 저장
	void insert(Plan plan);
	
	// 일정메인 수정값 저장하기
	void update(Plan plan);
	
	// 일정 삭제하기
	void delete(Plan plan);
	
	// 전체 일정 불러오기 
	List<Plan> selectPlanAllList();
	
	//제목으로 일정 검색 하기 
	Plan selectPlanTitle(Plan plan);
	
	
}
