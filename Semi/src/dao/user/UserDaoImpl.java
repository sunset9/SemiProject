package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utils.DBConn;
import dto.plan.Plan;
import dto.user.Bookmark;
import dto.user.User;

public class UserDaoImpl implements UserDao{

	private Connection conn = DBConn.getConnection();
	
	//update&insert&delete 는 커밋 추가!!
	
	//userid & userpw로 유저 조회 
	//존재하는 유저면 1 반환
	@Override
	public int selecCntUserByUseridAndUserpw(User user) {
		
		System.out.println("selecCntUserByUseridAndUserpw : "+user.getId());
		System.out.println("selecCntUserByUseridAndUserpw : "+user.getPassword());
		
		String sql = "";
		sql += "SELECT COUNT(*) FROM USERINFO";
		sql += " WHERE 1=1";
		if( user.getId() != null && user.getPassword() != null ) {
			sql += " AND id = ?";
			sql += " AND password = ?";
		}
		
		//DB 객체
		PreparedStatement ps = null; 
		ResultSet rs = null;
		
		int cnt = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			if( user.getId() != null && user.getPassword() != null ) {
				ps.setString(1, user.getId());
				ps.setString(2, user.getPassword());
//				System.out.println("selecCntUserByUseridAndUserpw is not null");
			}
			rs = ps.executeQuery();
			
			rs.next();
			
			cnt = rs.getInt(1);
			
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
		
		return cnt;
	}

	//id로 유저 검색해서 유저 정보 가져오기
	@Override
	public User selectUserByid(User u) {
		
		String sql = "";
		sql += "SELECT * FROM userinfo";
		sql += " WHERE 1 = 1";
		if(u.getId() != null) {
			sql += " AND id = ?";
		}
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		User user = new User();
		
		try {
			ps = conn.prepareStatement(sql);
			if(u.getId() != null) {
				ps.setString(1, u.getId());
			}
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				user.setUser_idx(rs.getInt("user_idx"));
				user.setId(rs.getString("id"));
				user.setPassword(rs.getString("password"));
				user.setNickname(rs.getString("nickname"));
				user.setProfile(rs.getString("profile"));
				user.setGrade(rs.getString("grade"));
				user.setSns_idx(rs.getInt("sns_idx"));
				user.setCreate_date(rs.getDate("create_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return user;
	}

	//회원가입처리(email)
	@Override
	public void insert(User user) {
		String sql = "";

		sql += "INSERT INTO userinfo(user_idx, id, password, nickname, profile, grade, sns_idx, create_date)";
		if(user.getSns_idx() == 1) {
			sql += " VALUES(userinfo_seq.nextval, ?, ?, ?, ?, '여행자', 1, sysdate)"; 
		} else if(user.getSns_idx() == 4) {
			sql += " VALUES(userinfo_seq.nextval, ?, ?, ?, ?, '여행자', 4, sysdate)";
		} else if(user.getSns_idx() == 3) {
			sql += " VALUES(userinfo_seq.nextval, ?, ?, ?, ?, '여행자', 3, sysdate)";
		}
		
		PreparedStatement ps = null;
		
		try {
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getId());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getNickname());
			if(user.getSns_idx() == 1) {
				ps.setString(4, "/image/basicProfile.png");
			} else if(user.getSns_idx() == 4) {
				ps.setString(4, user.getProfile());
			} else if(user.getSns_idx() == 3) {
				ps.setString(4, user.getProfile());
			}
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
	
	
	
	
	
	
	

	//id로 조회 후 회원탈퇴처리
	@Override
	public void delete(User user) {
		String sql = "";
		sql += "DELETE userinfo";

		sql += " WHERE id = ?";

		
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getId());
			System.out.println("dao delete() : "+user.getId());
			ps.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	//현재 로그인한 유저의 비밀번호 확인(탈퇴할때)
	@Override
	public int chechPw(User user) {
		String sql = "";
		sql += "SELECT * FROM userinfo";
		if(user.getPassword() != null) {
			sql += " WHERE id = ? AND password = ?";			
		}
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		int cnt = -1;
		try {
			ps = conn.prepareStatement(sql);
			if(user.getPassword() != null) {
				
				ps.setString(1, user.getPassword());
			}
			
			rs = ps.executeQuery();
			
			rs.next();
			
			cnt = rs.getInt(1);
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
		return cnt;
	}

	//유저정보 수정
	@Override
	public void update(User user) {
		
		String sql = "";
		sql += "UPDATE userinfo";
		sql += " SET nickname= ?, password=?";

		sql += " WHERE id=?";

		
		PreparedStatement ps = null;
		
		try {			
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getNickname());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getId());
			
		    ps.executeUpdate();
		    
		    conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<User> selectUserAll() {
		
		String sql = "";
		sql += "Select * from FROM userinfo";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<User> userList = new ArrayList<>();
		
		try {
			
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				User user = new User();
				
				user.setUser_idx(rs.getInt("user_idx"));
				user.setId(rs.getString("id"));
				user.setPassword(rs.getString("password"));
				user.setNickname(rs.getString("nickname"));
				user.setProfile(rs.getString("profile"));
				user.setGrade(rs.getString("gread"));
				user.setSns_idx(rs.getInt("sns_idx"));
				user.setCreate_date(rs.getDate("create_date"));
				
				userList.add(user);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
		
		return userList;
	}

	@Override
	public int checkid(User user) {
		String sql = "";
		sql += "SELECT COUNT(*) FROM userinfo";
		sql += " WHERE 1=1";
		sql += " AND id = ?";
		
		//System.out.println("유저아이디 : "+user.getId()); //ok

		//DB 객체
		PreparedStatement ps = null;
		ResultSet rs = null;

		int cnt = -1;

		try {
			// DB작업
			ps = conn.prepareStatement(sql);
			if (user.getId() != null) {
				ps.setString(1, user.getId());
			}
			rs = ps.executeQuery();

			rs.next();

			System.out.println(rs.getInt(1));
			cnt = rs.getInt(1);

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

		return cnt;
	}

	//닉네임 수정
	@Override
	public void changeNick(User user) {
		//id랑 nickname 받아서 해당 id인 회원의 nickname 변경
		//UPDATE USERINFO SET NICKNAME='e' WHERE id='e';
		
		String sql = "";
		sql += "UPDATE USERINFO";
		sql += " SET NICKNAME = ?";
		sql += " WHERE ID = ?";
		
		//DB 객체
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getNickname());
			ps.setString(2, user.getId());

		    ps.executeUpdate();

		    conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//비밀번호 수정
	@Override
	public void changePw(User user) {
		//id랑 password 받아서 해당 id인 회원의 password 변경
		// UPDATE USERINFO SET password='e' WHERE id='e';

		String sql = "";
		sql += "UPDATE USERINFO";
		sql += " SET PASSWORD = ?";
		sql += " WHERE ID = ?";

		// DB 객체
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getId());

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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	//내 일정 가져오기 
	@Override
	public List<Plan> getPlanner(User user) {
		
		String sql = "";
		sql += "SELECT p.PLAN_IDX, p.USER_IDX, p.START_DATE, p.END_DATE, p.TITLE, p.TRAVELED, p.OPENED, p.DISTANCE, p.CREATE_DATE, p.BannerURL";
		sql += " FROM USERINFO u JOIN PLANNER p";
		sql += " ON u.user_idx = p.user_idx";
		sql += " WHERE u.user_idx = ?";
		sql += " ORDER BY PLAN_IDX desc";
		
		
		//DB 객체
		PreparedStatement ps = null;
		ResultSet rs = null;	
		List<Plan> list = null;
		
		try {
			//DB 작업
			ps = conn.prepareStatement(sql);			
			ps.setInt(1, user.getUser_idx());
			rs = ps.executeQuery();
			
			list = new ArrayList<>();
			
			while(rs.next()) {
				Plan plan = new Plan();
				
				plan.setPlan_idx(rs.getInt("PLAN_IDX"));
				plan.setUser_idx(rs.getInt("USER_IDX"));
				plan.setStart_date(rs.getDate("START_DATE"));
				plan.setEnd_date(rs.getDate("END_DATE"));
				plan.setTitle(rs.getString("TITLE"));
				plan.setTraveled(rs.getInt("TRAVELED"));
				plan.setOpened(rs.getInt("OPENED"));
				plan.setDistance(rs.getInt("DISTANCE"));
				plan.setCreate_date(rs.getDate("CREATE_DATE"));
				plan.setBannerURL(rs.getString("BannerURL"));
				//System.out.println("userDao plan : "+plan);
				
				list.add(plan);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	
	//내 북마크 가져오기 
	@Override
	public List<Bookmark> getBookmarkList(User user) {
//		System.out.println("userDao getbList 유저 넘어왔나? : "+user); -> OK
		String sql = "";
		sql += "SELECT P.TITLE, P.BANNERURL";
		sql += " FROM USERINFO U JOIN BOOKMARK B";
		sql += " ON U.USER_IDX = B.USER_IDX";
		sql += " JOIN PLANNER P";
		sql += " ON B.PLAN_IDX = P.PLAN_IDX";
		sql += " WHERE U.USER_IDX = ?";
		
		//DB 객체
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Bookmark> list = null;
		
		try {
			//DB 작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getUser_idx());
			rs = ps.executeQuery();
			
			list = new ArrayList<>();

			while (rs.next()) {
				Bookmark bMark = new Bookmark();
				
				bMark.setTitle(rs.getString("TITLE"));
				bMark.setBannerURL(rs.getString("BANNERURL"));
				
				list.add(bMark);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	//내 일정 포스팅 개수 가져오기
	@Override
	public int getCntPlan(User user) {
		String sql = "";
		sql += "SELECT COUNT(*)";
		sql += " FROM USERINFO U JOIN PLANNER P ";
		sql += " ON U.USER_IDX = P.USER_IDX";
		sql += " WHERE U.USER_IDX = ?";
		
		//DB 객체
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int cnt = -1;
		
	    try {
	    	//DB 작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getUser_idx());
			
			rs = ps.executeQuery();
			
			rs.next();
			
			cnt = rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	    
		return cnt;
	}

	//내 일정들에서 여행거리 리스트에 담기 
	@Override
	public int getTotDist(User user) {
		String sql = "";
		sql += "SELECT SUM(DISTANCE) FROM PLANNER";
		sql += " WHERE USER_IDX = ?";
		
		//DB객체
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getUser_idx());
			
			rs = ps.executeQuery();
			
			rs.next();
			
			cnt = rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return cnt;
	}

	@Override
	public User selectUserByUserIdx(User u) {
		String sql = "";
		sql += "SELECT * FROM userinfo";
		sql += " WHERE user_idx = ?";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		User user = new User();
		
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, u.getUser_idx());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				user.setUser_idx(rs.getInt("user_idx"));
				user.setId(rs.getString("id"));
				user.setPassword(rs.getString("password"));
				user.setNickname(rs.getString("nickname"));
				user.setProfile(rs.getString("profile"));
				user.setGrade(rs.getString("grade"));
				user.setSns_idx(rs.getInt("sns_idx"));
				user.setCreate_date(rs.getDate("create_date"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return user;
	}

	
	// --------------------------------------------------------
	// 관리자 페이지에서 쓰는 메소드 
	
	@Override
	public int selectUserCnt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectTouristCnt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectAuthorCnt() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectManagerCnt() {
		// TODO Auto-generated method stub
		return 0;
	}


}
