package dao.plan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import dto.Account.Account;
import dto.plan.Plan;
import dto.user.User;
import utils.DBConn;
import utils.Paging;

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
				planInfo.setCreate_date( rs.getDate("create_date") );
				planInfo.setBannerURL( rs.getString("bannerURL") );
				
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
		return planInfo;
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
			userInfo.setTotalPlanCnt(selectPlanCntAll(user.getUser_idx()));
			
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
			userInfo.setTotalPlanCnt(selectPlanCntAll(plan.getUser_idx()));
			
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
	public int selectPlanCntAll(int user_idx) {
//			전체 게시글 수의 합 쿼리
		//planner 조회 쿼리
		String sql = "";
		sql += "SELECT COUNT(*) FROM planner";
		sql += " WHERE user_idx = ?";
		
		int plan_cnt = 0;
		
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, user_idx);
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
	@Override
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
	@Override
	public boolean deletePlanByPlanIdx(Plan plan) {
		String sql = "delete planner"
				+ " where plan_idx = ?";
		
		boolean rs = false;
		try {
			// 오토커밋 해제
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, plan.getPlan_idx());
			
			int i =ps.executeUpdate();
			
			if(i==1) {
				rs = true;
			}
			
			conn.commit();
			
		} catch (SQLException e) {
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
		return rs ;
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
		sql += "INSERT INTO PLANNER(plan_idx, user_idx, start_date, end_date, title, traveled, opened, distance, bannerURL)";
		sql += " VALUES (?, ?, to_date(?, 'yyyy-MM-dd'), to_date(?, 'yyyy-MM-dd'), ?, ?, ?, 0, ?)";
		
		PreparedStatement ps = null;
		
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, plannerSeqNextval);
			
			ps.setInt(2, user.getUser_idx());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd",Locale.KOREA);
			
			String sD = sdf.format(param.getStart_date());
			System.out.println("plandaoimpl start date : "+sD);
			ps.setString(3, sD);
			
			String eD = sdf.format(param.getEnd_date());
			System.out.println("plandaoimpl end date : "+eD);
			ps.setString(4, eD);
			
			ps.setString(5, param.getTitle());
			ps.setInt(6, param.getTraveled());
			ps.setInt(7, param.getOpened());
			ps.setString(8, "/image/basicBanner.png");
			
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
						planInfo.setCreate_date( rs.getDate("create_date") );
						planInfo.setBannerURL( rs.getString("bannerURL") );
						
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
				return planInfo;
	}

//	@Override
//	public List<Integer> sumCategoryByPlanIdx(Plan plan) {
//
//		//전체 게시글 수 조회 쿼리
//		String sql = "";
//		sql += "select sum(origin_cost) "
//				+ "from account "
//				+ "where acc_cat_idx = ? "
//				+ "and plan_idx = ?";
//		
//		List<Integer> sumInt = new ArrayList<>();
//		
//		try {
//			for(int test = 1; test<9; test++) {
//				ps = conn.prepareStatement(sql);
//				ps.setInt(1, test);
//				System.out.println("1234" + test);
//				ps.setInt(2, plan.getPlan_idx());
//				rs = ps.executeQuery();
//				rs.next();
//				
//				while(rs.next()) {	
//					
//					int cnt;
//					
//					cnt = rs.getInt(1);
//					
//					sumInt.add(cnt);
//					
//					System.out.println("5687" + sumInt);
//				}
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if(ps!=null)	ps.close();
//				if(rs!=null)	rs.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return sumInt;
//	}
	
	@Override
	public int sumAirfareByPlanIdx(Plan plan) {

		//전체 게시글 수 조회 쿼리
		String sql = "";
		sql += "select sum(caled_cost) "
				+ "from account "
				+ "where acc_cat_idx = 1 "
				+ "and plan_idx = ?";
		
		int cnt = 0;
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, plan.getPlan_idx());
			rs = ps.executeQuery();
			rs.next();
			
			
			
			cnt = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null)	ps.close();
				if(rs!=null)	rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cnt;
	}

	@Override
	public int sumTrafficByPlanIdx(Plan plan) {
		//전체 게시글 수 조회 쿼리
				String sql = "";
				sql += "select sum(caled_cost) "
						+ "from account "
						+ "where acc_cat_idx = 2 "
						+ "and plan_idx = ?";
				
				int cnt = 0;
				
				try {
					
					ps = conn.prepareStatement(sql);
					ps.setInt(1, plan.getPlan_idx());
					rs = ps.executeQuery();
					rs.next();
					
					
					
					cnt = rs.getInt(1);
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						if(ps!=null)	ps.close();
						if(rs!=null)	rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				return cnt;
	}

	@Override
	public int sumStayByPlanIdx(Plan plan) {
		//전체 게시글 수 조회 쿼리
				String sql = "";
				sql += "select sum(caled_cost) "
						+ "from account "
						+ "where acc_cat_idx = 3 "
						+ "and plan_idx = ?";
				
				int cnt = 0;
				
				try {
					
					ps = conn.prepareStatement(sql);
					ps.setInt(1, plan.getPlan_idx());
					rs = ps.executeQuery();
					rs.next();
					
					
					
					cnt = rs.getInt(1);
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						if(ps!=null)	ps.close();
						if(rs!=null)	rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				return cnt;
	}

	@Override
	public int sumAdmissionByPlanIdx(Plan plan) {
		//전체 게시글 수 조회 쿼리
				String sql = "";
				sql += "select sum(caled_cost) "
						+ "from account "
						+ "where acc_cat_idx = 4 "
						+ "and plan_idx = ?";
				
				int cnt = 0;
				
				try {
					
					ps = conn.prepareStatement(sql);
					ps.setInt(1, plan.getPlan_idx());
					rs = ps.executeQuery();
					rs.next();
					
					
					
					cnt = rs.getInt(1);
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						if(ps!=null)	ps.close();
						if(rs!=null)	rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				return cnt;
	}

	@Override
	public int sumFoodByPlanIdx(Plan plan) {
		//전체 게시글 수 조회 쿼리
				String sql = "";
				sql += "select sum(caled_cost) "
						+ "from account "
						+ "where acc_cat_idx = 5 "
						+ "and plan_idx = ?";
				
				int cnt = 0;
				
				try {
					
					ps = conn.prepareStatement(sql);
					ps.setInt(1, plan.getPlan_idx());
					rs = ps.executeQuery();
					rs.next();
					
					
					
					cnt = rs.getInt(1);
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						if(ps!=null)	ps.close();
						if(rs!=null)	rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				return cnt;
	}

	@Override
	public int sumPlayByPlanIdx(Plan plan) {
		//전체 게시글 수 조회 쿼리
				String sql = "";
				sql += "select sum(caled_cost) "
						+ "from account "
						+ "where acc_cat_idx = 6 "
						+ "and plan_idx = ?";
				
				int cnt = 0;
				
				try {
					
					ps = conn.prepareStatement(sql);
					ps.setInt(1, plan.getPlan_idx());
					rs = ps.executeQuery();
					rs.next();
					
					
					
					cnt = rs.getInt(1);
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						if(ps!=null)	ps.close();
						if(rs!=null)	rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				return cnt;
	}

	@Override
	public int sumShopByPlanIdx(Plan plan) {
		String sql = "";
		sql += "select sum(caled_cost) "
				+ "from account "
				+ "where acc_cat_idx = 7 "
				+ "and plan_idx = ?";
		
		int cnt = 0;
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, plan.getPlan_idx());
			rs = ps.executeQuery();
			rs.next();
			
			
			
			cnt = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null)	ps.close();
				if(rs!=null)	rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cnt;
	}

	@Override
	public int sumEtcByPlanIdx(Plan plan) {
		String sql = "";
		sql += "select sum(caled_cost) "
				+ "from account "
				+ "where acc_cat_idx = 8 "
				+ "and plan_idx = ?";
		
		int cnt = 0;
		
		try {
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, plan.getPlan_idx());
			rs = ps.executeQuery();
			rs.next();
			
			
			
			cnt = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null)	ps.close();
				if(rs!=null)	rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cnt;
	}
	

	@Override
	public int selectCntAll(int searchType, String search) {
		System.out.println("검색어 : "+search);
		System.out.println("검색타입 : "+searchType);
		
		
		// 전체 게시글 수 조회
		String sql = "SELECT COUNT(*) FROM planner   ";

		if(searchType == 1) {
			sql += "WHERE title ";
		} else if(searchType == 2) {
			sql += "WHERE nickname ";
		}
		
		if (search != null && !"".equals(search)) {
			sql += " LIKE '%"+search+"%'";
		}

		// DB객체 생성
		PreparedStatement ps = null;
		ResultSet rs = null;

		// 조회 결과 저장할 변수 생성 =
		int cnt = 0;

		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			rs.next();

			// 조회 결과중 첫 번째 컬럼값 가져오기
			cnt = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

	//페이징 된 Plan 리스트 조회
	@Override
	public List<Plan> selectPagingList(Paging paging) {
		// 페이징 리스트 조회 쿼리
		String sql = "SELECT * FROM( SELECT rownum rnum, PL.* FROM ( SELECT plan_idx, P.user_idx, " ;
			   sql +=	"(SELECT nickname FROM userinfo U WHERE U.user_idx = P.user_idx )nickname  " ;
			   sql +=", start_date, end_date, title, traveled, opened, bannerurl, create_date  " ;
			   sql +="FROM planner P  ORDER BY user_idx DESC " ; 
			   sql +=") PL  " ;
			   
			   // searchType 1이면 제목으로 조회
			   if(paging.getSearchType()==1) {
			   sql +="WHERE title  " ; 
			   }else if (paging.getSearchType()==2) {
				   // searchType 2이면 닉네임으로 조회
				   sql+= "WHERE nickname";
			   }
			   
			   if(paging.getSearch()!=null && !"".equals(paging.getSearch())) {
					sql += "  LIKE '%"+paging.getSearch()+"%'";
				}
			   sql +="ORDER BY rnum   ) WHERE rnum between ? AND ?";
		

		// DB 객체 생성
		PreparedStatement ps = null;
		ResultSet rs = null;

		// 조회 결과 담을 list 생성
		List<Plan> list = new ArrayList<>();

		try {
			// DB 작업 실행
			ps = conn.prepareStatement(sql);

			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());

			rs = ps.executeQuery();

			// 조회 결과 List에 담기
			while (rs.next()) {
				Plan p = new Plan();
				
				
			// rs의 결과 DTO에 하나씩 저장하기
				p.setPlan_idx(rs.getInt("plan_idx"));
				p.setUser_idx(rs.getInt("user_idx"));
				p.setStart_date(rs.getDate("start_date"));
				p.setEnd_date(rs.getDate("end_date"));
				p.setOpened(rs.getInt("opened"));
				p.setTitle(rs.getString("title"));
				p.setBannerURL(rs.getString("bannerurl"));
				p.setNick(rs.getString("nickname"));
				
				p.setCreate_date(rs.getDate("create_date"));
				

//				// 조회 결과 List에 넣기
				list.add(p);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// DB객체 닫기
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 결과 반환
		return list;
	}
	
	// 유저의 profile 변경
	@Override
	public void bannerUpdate(Plan plan) {
		String sql = "update planner set bannerurl = ? where plan_idx= ?";
		
		System.out.println("UserDaoImpl : "+plan.getBannerURL());
		
		//DB 객체
		PreparedStatement ps = null;

		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);

			ps.setString(1, plan.getBannerURL());
			ps.setInt(2, plan.getPlan_idx());

			ps.executeUpdate();

			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
				
	}
}
