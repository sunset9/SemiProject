package dao.plan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import dto.Account.Account;
import dto.plan.Plan;
import dto.user.User;
import utils.DBConn;

public class PlanDaoImpl implements PlanDao{

	private Connection conn = DBConn.getConnection();
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	// 일정메인 아이디로 일정메인 정보, 북마크 정보 불러오기
	@Override
	public Plan selectPlanInfoByPlanIdx(int plan_idx) {
		//planner 조회 쿼리
		String sql = "";
		sql += "SELECT * FROM planner";
		sql += " WHERE plan_idx = ?";
		
		//조회 결과 담을 DTO
		Plan planInfo = new Plan();
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, plan_idx);
			rs = ps.executeQuery();
			
			//결과 담기
			while(rs.next()) {
				
				//결과 행 DTO에 저장
				planInfo.setPlan_idx( rs.getInt("plan_idx") );
				planInfo.setUser_idx( rs.getInt("user_idx") );
				planInfo.setStart_date( rs.getDate("start_date") );
				planInfo.setEnd_date( rs.getDate("end_date") );
				planInfo.setTitle( rs.getString("title") );
				planInfo.setTraveled( rs.getInt("traveled") );
				planInfo.setOpened( rs.getInt("opened") );
				planInfo.setDistance( rs.getInt("distance") );
				planInfo.setCreate_date( rs.getDate("create_date") );
				
			}
			
			planInfo.setTot_dist(selectTotalDistance());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB객체 닫기
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 전체조회 결과 반환
		return planInfo;
	}
	
	// 유저의 전체 게시글의 총 거리 계산하기
	@Override
	public int selectTotalDistance() {
		//planner 조회 쿼리
		String sql = "";
		sql += "SELECT SUM(distance) FROM planner";
		sql += " WHERE user_idx = ?";
		
		int tot_dist = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			//plan.getUser_idx()
			ps.setInt(1, 1);
			rs = ps.executeQuery();
			//결과 담기
			rs.next();
			
			//결과 행 DTO에 저장
			tot_dist = rs.getInt(1) ;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB객체 닫기
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 전체조회 결과 반환
		return tot_dist;
	}
	
	// 유저 아이디로 유저 정보 불러오기 -> 게시자 정보
	@Override
	public User selectUserInfoByUserIdx(User user) {
		//planner 조회 쿼리
		String sql = "";
		sql += "SELECT * FROM userinfo";
		sql += " WHERE user_idx = ?";
		
		//조회 결과 담을 DTO
		User userInfo = new User();
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getUser_idx());
			rs = ps.executeQuery();
			
			//결과 담기
			while(rs.next()) {
				
				//결과 행 DTO에 저장
				userInfo.setUser_idx( rs.getInt("user_idx") );
				userInfo.setId( rs.getString("id") );
				userInfo.setPassword( rs.getString("password") );
				userInfo.setNickname( rs.getString("nickname") );
				userInfo.setProfile( rs.getString("profile") );
				userInfo.setGrade( rs.getString("grade") );
				userInfo.setSns_idx( rs.getInt("sns_idx") );
				userInfo.setCreate_date( rs.getDate("create_date") );
			}
			userInfo.setTotalPlanCnt(selectPlanCntAll());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB객체 닫기
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 전체조회 결과 반환
		return userInfo;
	}
	
	// 유저 아이디로 유저 정보 불러오기 -> 게시자 정보
		@Override
	public User selectUserInfoByUserIdx(Plan plan) {
		//planner 조회 쿼리
		String sql = "";
		sql += "SELECT * FROM userinfo";
		sql += " WHERE user_idx = ?";
		
		//조회 결과 담을 DTO
		User userInfo = new User();
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, plan.getUser_idx());
			rs = ps.executeQuery();
			
			//결과 담기
			while(rs.next()) {
				
				//결과 행 DTO에 저장
				userInfo.setUser_idx( rs.getInt("user_idx") );
				userInfo.setId( rs.getString("id") );
				userInfo.setPassword( rs.getString("password") );
				userInfo.setNickname( rs.getString("nickname") );
				userInfo.setProfile( rs.getString("profile") );
				userInfo.setGrade( rs.getString("grade") );
				userInfo.setSns_idx( rs.getInt("sns_idx") );
				userInfo.setCreate_date( rs.getDate("create_date") );
			}
			userInfo.setTotalPlanCnt(selectPlanCntAll());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB객체 닫기
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 전체조회 결과 반환
		return userInfo;
	}
		
	// 유저의 전체 게시글 수 가져오기
	public int selectPlanCntAll() {
//			전체 게시글 수의 합 쿼리
		//planner 조회 쿼리
		String sql = "";
		sql += "SELECT COUNT(*) FROM planner";
		sql += " WHERE user_idx = ?";
		
		int plan_cnt = 0;
		
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			//plan.getUser_idx()
			ps.setInt(1, 1);
			rs = ps.executeQuery();
			//결과 담기
			rs.next();
			
			//결과 행 DTO에 저장
			plan_cnt = rs.getInt(1) ;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB객체 닫기
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 전체조회 결과 반환
		return plan_cnt;
	}
		
	// 플랜의 가계부 인덱스로 가계부 정보 불러오기
	public Account selectAccountInfoByAccountIdx(Plan plan) {
		
		//planner 조회 쿼리
		String sql = "";
		sql += "SELECT * FROM account";
		sql += " WHERE plan_idx = ?";
		
		//조회 결과 담을 DTO
		Account accInfo = new Account();
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, plan.getPlan_idx());
			rs = ps.executeQuery();
			
			//결과 담기
			while(rs.next()) {
				
				//결과 행 DTO에 저장
				accInfo.setAcc_idx( rs.getInt("acc_idx") );
				accInfo.setPlan_idx( rs.getInt("plan_idx") );
				accInfo.setStory_idx( rs.getInt("story_idx") );
//				accInfo.setCurr_idx( rs.getInt("curr_idx") );
//				accInfo.setAcc_cat_idx( rs.getInt("acc_cat_idx") );
				accInfo.setOrigin_cost( rs.getInt("origin_cost") );
				accInfo.setCaled_cost( rs.getInt("caled_cost") );
				accInfo.setCreate_date( rs.getDate("create_date") );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB객체 닫기
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 전체조회 결과 반환
		return accInfo;
	}
	
	// 새로운 일정 저장
	@Override
	public void insert(Plan plan) {
		String sql = "INSERT INTO timetable(ttb_idx, plan_idx, loc_idx, start_time, end_time)"
				+ " VALUES(timetable_seq.nextval, ?, ?"
				+ ", TO_DATE(?, 'yyyy/mm/dd hh24:mi')"
				+ ", TO_DATE(?, 'yyyy/mm/dd hh24:mi')"
				+ " )";

		try {
			// 오토커밋 해제
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(sql);
			
			
			ps.executeUpdate();
			
			conn.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 일정메인 수정값 저장하기
	@Override
	public void update(Plan plan) {
		String sql = "UPDATE planner set"
				+ " start_date=TO_DATE(?, 'yyyy/mm/dd hh24:mi'),"
				+ " end_date=TO_DATE(?, 'yyyy/mm/dd hh24:mi'),"
				+ " title=?, traveled=?, opened=?"
				+ " where plan_idx=?";

		try {
			// 오토커밋 해제
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, new SimpleDateFormat("yyyy-MM-dd HH:mm").format(plan.getStart_date()));
			ps.setString(2, new SimpleDateFormat("yyyy-MM-dd HH:mm").format(plan.getEnd_date()));
			ps.setString(3, plan.getTitle());
			ps.setInt(4, plan.getTraveled());
			ps.setInt(5, plan.getOpened());
			ps.setInt(6, plan.getPlan_idx());
			
			ps.executeUpdate();
			
			conn.commit();
			
			System.out.println("update : " + plan);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 일정 삭제하기
	public void delete(Plan plan) {
		String sql = "drop table planner"
				+ " where ?";
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

	//planner_seq.nextval 얻어오기 
	@Override
	public int getPlannerSeqNextval() {
		int value = 0;
		
		String sql = "SELECT planner_seq.nextval FROM dual";
		
		//DB 객체
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();

			rs.next();

			value = rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				//DB객체 닫기
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return value;
	}
	
	//새일정만들기 파라미터 저장
	@Override
	public void insertPlan(Plan param, User user) {
		int plannerSeqNextval = getPlannerSeqNextval();
		System.out.println("plandaoimpl plannerSeqNextval : "+plannerSeqNextval);
		String sql = "";
		sql += "INSERT INTO PLANNER(plan_idx, user_idx, start_date, end_date, title, traveled, opened, distance, bannerurl)";
		sql += " VALUES (?, ?, to_date(?, 'yyyy-MM-dd'), to_date(?, 'yyyy-MM-dd'), ?, ?, 0, 0, '/upload/user/paris.jpg')";
		
		PreparedStatement ps = null;
		
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, plannerSeqNextval);
			
			ps.setInt(2, user.getUser_idx());
			
			DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy",Locale.ENGLISH);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd",Locale.KOREA);
			
			String sD = sdf.format(param.getStart_date());
			System.out.println("plandaoimpl start date : "+sD);
			ps.setString(3, sD);
			
			String eD = sdf.format(param.getEnd_date());
			System.out.println("plandaoimpl end date : "+eD);
			ps.setString(4, eD);
			
			ps.setString(5, param.getTitle());
			ps.setInt(6, param.getTraveled());
			
			ps.executeUpdate();
			
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	//plan_idx 가져오기
	@Override
	public int getPlan_idx() {
		String sql = "SELECT MAX(PLAN_IDX) FROM PLANNER";
		
		//DB 객체
		PreparedStatement ps = null;
		ResultSet rs = null;

		int plan_idx = -1;

		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			rs.next();
			
			plan_idx = rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				//DB객체 닫기
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return plan_idx;
	}

	@Override
	public Plan selectPlanInfoByPlanIdx(Plan plan) {
		//planner 조회 쿼리
				String sql = "";
				sql += "SELECT * FROM planner";
				sql += " WHERE plan_idx = ?";
				
				//조회 결과 담을 DTO
				Plan planInfo = new Plan();
				try {
					//DB작업
					ps = conn.prepareStatement(sql);
					ps.setInt(1, plan.getPlan_idx());
					rs = ps.executeQuery();
					
					//결과 담기
					while(rs.next()) {
						
						//결과 행 DTO에 저장
						planInfo.setPlan_idx( rs.getInt("plan_idx") );
						planInfo.setUser_idx( rs.getInt("user_idx") );
						planInfo.setStart_date( rs.getDate("start_date") );
						planInfo.setEnd_date( rs.getDate("end_date") );
						planInfo.setTitle( rs.getString("title") );
						planInfo.setTraveled( rs.getInt("traveled") );
						planInfo.setOpened( rs.getInt("opened") );
						planInfo.setDistance( rs.getInt("distance") );
						planInfo.setCreate_date( rs.getDate("create_date") );
						
					}
					
					planInfo.setTot_dist(selectTotalDistance());
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						//DB객체 닫기
						if(rs!=null)	rs.close();
						if(ps!=null)	ps.close();
						
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				// 전체조회 결과 반환
				return planInfo;
	}
	
}
