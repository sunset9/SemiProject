package dao.plan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import dto.Account.Account;
import dto.plan.Plan;
import dto.user.User;
import utils.DBConn;

public class PlanDaoImpl implements PlanDao{

	private Connection conn = DBConn.getConnection();
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	// 일정메인 아이디로 일정메인 정보, 북마크 정보 불러오기
	public Plan getPlanInfoByPlanIdx(Plan plan) {
		String sql = "select * from plan order by plan_idx";
		Plan pl;
		return null;  
	}
	
	// 유저 아이디로 유저 정보 불러오기 -> 게시자 정보
	public User getUserInfoByUserIdx1(Plan plan) {
		String sql = "select * from user order by user_idx";
		User us;
		return null;
	}
	
	// 유저의 전체 게시글 수 가져오기
	public int selectPlanCntAll() {
//			전체 게시글 수의 합 쿼리
		String sql = "select count(*) from planner";
		int plan_cnt = 0;
		return plan_cnt;
	}
	
	// 유저의 전체 게시글의 총 거리 계산하기 
	public int selectTotalDistance() {
//			게시글별 총거리 합 쿼리
		String sql = "select sum(distance) from planner";
		int tot_dist = 0;
		return tot_dist;
	}
		
	// 플랜의 가계부 인덱스로 가계부 정보 불러오기
	public Account getAccountInfoByAccountIdx(Plan plan) {
		String sql = "select * from account order by acc_idx";
		Account ac;
		return null;
	}
	
	// 새로운 일정 저장
	public void insert() {
		
		
	}
	
	// 일정메인 수정값 저장하기
	public void update(Plan plan) {
		String sql = "update table planner"
				+ " where ?";
	}
	
	// 일정 삭제하기
	public void delete(Plan plan) {
		String sql = "drop table planner"
				+ " where ?";
	}

	@Override
	public User getUserInfoByUserIdx(Plan plan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Plan> selectPlanAllList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Plan selectPlanTitle(Plan plan) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
