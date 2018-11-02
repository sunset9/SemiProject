package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.plan.Plan;
import dto.timetable.Location;
import dto.user.Bookmark;
import dto.user.UploadFile;
import dto.user.User;
import utils.DBConn;
import utils.Paging;

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
				user.setStatus(rs.getInt("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return user;
	}

	//회원가입처리(email)
	@Override
	public void insert(User user) {
		System.out.println("userDao "+user);
		String sql = "";

		sql += "INSERT INTO userinfo(user_idx, id, password, nickname, profile, grade, sns_idx, create_date, status)";
		if(user.getSns_idx() == 1) {
			sql += " VALUES(userinfo_seq.nextval, ?, ?, ?, ?, '여행자', 1, sysdate, 1)"; 
		} else if(user.getSns_idx() == 4) {
			sql += " VALUES(userinfo_seq.nextval, ?, ?, ?, ?, '여행자', 4, sysdate, 1)";
		} else if(user.getSns_idx() == 3) {
			sql += " VALUES(userinfo_seq.nextval, ?, ?, ?, ?, '여행자', 3, sysdate, 1)";
		} else if(user.getSns_idx() == 2) {
			sql += " VALUES(userinfo_seq.nextval, ?, ?, ?, ?, '여행자', 2, sysdate, 1)";
		}

		//sql += " VALUES(userinfo_seq.nextval, ?, ?, ?, ?, '여행자', "+user.getSns_idx()+", sysdate)";
		
		PreparedStatement ps = null;
		
		try {
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getId());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getNickname());
			if(user.getSns_idx() == 1) {
				ps.setString(4, "/upload/user/basicProfile.png");
			} else {
				ps.setString(4, user.getProfile());
			}

//			if(user.getSns_idx() == 1) {
//				ps.setString(4, "/image/basicProfile.png");
//			} else {
//				ps.setString(4, user.getProfile());
//			}
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

	//회원탈퇴처리
	//STATUS = 0 : 탈퇴회원
	@Override
	public int delete(User user) {
		
		String sql = "";
		sql += "UPDATE userinfo SET id = null, "
				+ "PROFILE = '/upload/user/basicProfile.png', "
				+ "status = 0 "
				+ "where user_idx = ?";
		
		PreparedStatement ps = null;
		int rs = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getUser_idx());
			rs = ps.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rs ;
		
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
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null) ps.close();
			} catch (SQLException e) {
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
				user.setStatus(rs.getInt("status"));
				userList.add(user);
				
			}
			
		} catch (SQLException e) {
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
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null) ps.close();
			} catch (SQLException e) {
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

	//내 일정 가져오기 
	@Override
	public List<Plan> getPlanner(User user) {
		
		String sql = "";
		sql += "SELECT p.PLAN_IDX, p.USER_IDX, p.START_DATE, p.END_DATE, p.TITLE, p.TRAVELED, p.OPENED, p.CREATE_DATE, p.BannerURL";
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
				plan.setCreate_date(rs.getDate("CREATE_DATE"));
				plan.setBannerURL(rs.getString("BannerURL"));
				//System.out.println("userDao plan : "+plan);
				
				list.add(plan);
			}
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
		
		return list;
	}

	
	//내 북마크 가져오기 
	@Override
	public List<Bookmark> getBookmarkList(User user) {
//		System.out.println("userDao getbList 유저 넘어왔나? : "+user); -> OK
		String sql = "";
		sql += "SELECT B.book_idx, P.TITLE, P.BANNERURL, P.plan_idx, B.user_idx, U.nickname" ;
		sql +=		"        FROM BOOKMARK B"  ;
		sql +=		"        INNER JOIN PLANNER P" ; 
		sql +=		"        ON B.PLAN_IDX = P.PLAN_IDX" ; 
		sql +=		"        INNER JOiN USERINFO U" ; 
		sql +=		"        ON P.user_idx = U.user_idx" ; 
		sql +=		"    WHERE B.USER_IDX =? ";
	
		
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
				Bookmark b = new Bookmark();
				
				b.setBook_idx(rs.getInt("book_idx"));
				b.setPlan_idx(rs.getInt("plan_idx"));
				b.setUser_idx(rs.getInt("user_idx"));
				b.setWriteNick(rs.getString("nickname"));
				b.setTitle(rs.getString("TITLE"));
				b.setBannerURL(rs.getString("BANNERURL"));
				
				list.add(b);
			}
			
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
				if(ps!=null)	ps.close();
				if(rs!=null)	rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	    
		return cnt;
	}


	//내 일정들에서 여행거리 리스트에 담기 
	@Override
	public List<Location> getTotDist(Plan plan) {
		String sql = "SELECT lat, lng FROM LOCATION L"
				+ " RIGHT JOIN timetable T"
				+ " ON L.loc_idx = T.loc_idx"
				+ " WHERE T.plan_idx = ?"
				+ " ORDER BY T.start_time";
		
		
		List<Location> latLngList = new ArrayList<>();
		
		//DB 객체
		PreparedStatement ps = null;
		ResultSet rs = null;
				
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, plan.getPlan_idx());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Location loc = new Location();
				
				loc.setLat(rs.getDouble("lat"));
				loc.setLng(rs.getDouble("lng"));
				
				latLngList.add(loc);
			}
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
		
		return latLngList;
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
				e.printStackTrace();
			}
		}
		
		return user;
	}

	
	// --------------------------------------------------------
	// 관리자 페이지에서 쓰는 메소드 
	
	@Override
	public int selectUserCnt(String grade) {
		String sql = "";
			   sql +="SELECT COUNT(*) FROM userinfo ";
	
		if(grade != null) {
			sql +=" where grade ='"+grade+"'";   
		 }
		
		// 조회 결과 담을 변수
		int cnt = 0;
		
		PreparedStatement ps = null;
		ResultSet rs = null;	   
		
		try {
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while( rs.next()) {
				cnt = rs.getInt(1);
			}
			
		} catch (SQLException e) {
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
	public int selectUserCnt(String search, int searchType) {
		
		String sql = "SELECT COUNT(*) FROM userinfo  ";
		
		if(searchType == 1) {
			sql += "WHERE nickname ";
		} else if(searchType == 2) {
			sql += "WHERE id ";
		}
		
		if(search !=null && !"".equals(search)) {
			sql += " LIKE '%"+search+"%'";
		}
		
		// DB 객체 생성 
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			rs.next();
			
			cnt = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
	public List<User> selectList(Paging paging) {

		System.out.println("searchType : " +paging.getSearchType());
		System.out.println("search: " +paging.getSearch());
		
		// 조건으로 검색된 리스트 조회 쿼리
		String sql="";
		sql += "SELECT * FROM( " ;
		sql += 	"    SELECT rownum rnum, U.* FROM ( " ;
		sql += 	"SELECT user_idx, id, nickname, profile, grade, ";
		sql	+= "( SELECT sns_name FROM snstype S WHERE S.sns_idx = UI.sns_idx) sns_name ,create_date  ";
		sql	+= "FROM userinfo UI  ORDER BY user_idx DESC " ;
		sql += 	"       ) U  " ;
		
		if(paging.getSearchType()==1) {
			sql+= "WHERE nickname";
		} else if(paging.getSearchType()==2) {
			sql+= "WHERE id";
		}
		if(paging.getSearch()!=null && !"".equals(paging.getSearch())) {
			sql += "  LIKE '%"+paging.getSearch()+"%'";
		}
		
		sql += 	"        ORDER BY rnum" ;
		sql += 	"    )" ;
		sql += 	"  WHERE rnum between ? AND ?";
		
		// DB 객체 생성 
		PreparedStatement ps = null;
		ResultSet rs = null; 
		
		// 조회 결과 담을 list 생성
		List<User> list = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				User u = new User();
				u.setUser_idx(rs.getInt("user_idx"));
				u.setSnsType(rs.getString("sns_name"));
				u.setId(rs.getString("id"));
				u.setNickname(rs.getString("nickname"));
				u.setGrade(rs.getString("grade"));
				u.setCreate_date(rs.getDate("create_date"));
				u.setProfile(rs.getString("profile"));
				u.setStatus(rs.getInt("status"));
			
				list.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				//DB객체 닫기
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return list;
	}

	@Override
	public void deleteUserList(String names) {
		String sql = "DELETE FROM userinfo WHERE user_idx IN("+names+")";
		
		//DB 객체
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(sql);
					
			ps.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null)	ps.close();
						
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public String selectGrade(User user) {
		String sql="SELECT grade FROM userinfo WHERE user_idx = ?";
		
		//DB 객체
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		// 결과 담을 변수
		String grade =null;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				grade = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null)	ps.close();
						
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return grade;
	}

	@Override
	public int updateGrade(User user) {
		String sql="UPDATE userinfo SET grade = ?  WHERE user_idx =?";
		
		//DB 객체
		PreparedStatement ps = null;
		
		
		// 결과 담을 변수
		int result=0;
		
		try {
			conn.setAutoCommit(false);
			
			ps =conn.prepareStatement(sql);
			ps.setString(1, user.getGrade());
			ps.setInt(2, user.getUser_idx());
			
			result = ps.executeUpdate();
			
			conn.commit();
		
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null)	ps.close();
						
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	//현재 유저의 글을 제외한 모든 글 가져오기
	@Override
	public List<Plan> getAllPlanList(User cUser) {
		System.out.println(cUser);
		
		String sql = "SELECT * FROM PLANNER WHERE USER_IDX != ?";
		
		//DB 객체
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Plan> list = null;
		
		try {
			//DB 작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, cUser.getUser_idx());
			rs = ps.executeQuery();
			
			list = new ArrayList<>();

			while (rs.next()) {
				Plan plan = new Plan();
				
				plan.setPlan_idx(rs.getInt("PLAN_IDX"));
				plan.setUser_idx(rs.getInt("USER_IDX"));
				plan.setStart_date(rs.getDate("START_DATE"));
				plan.setEnd_date(rs.getDate("END_DATE"));
				plan.setTitle(rs.getString("TITLE"));
				plan.setTraveled(rs.getInt("TRAVELED"));
				plan.setOpened(rs.getInt("OPENED"));
				plan.setCreate_date(rs.getDate("CREATE_DATE"));
				plan.setBannerURL(rs.getString("BANNERURL"));
				//System.out.println("userDaoImpl plan : "+plan);
				
				list.add(plan);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null)	ps.close();
						
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	//파일 업로드 정보 입력
	@Override
	public void insert(UploadFile file) {
		System.out.println("UserDaoImpl insert() : "+file);
		String sql = "INSERT INTO uploaduserfile( origin_name, stored_name )";
		sql += " VALUES ( ?, ? )";
		
		PreparedStatement ps = null;
		
		try {
			conn.setAutoCommit(false);

			ps = conn.prepareStatement(sql);
			
			ps.setString(1, file.getOrigin_name());
			ps.setString(2, file.getStored_name());
			
			ps.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(ps!=null)	ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	// 유저의 profile 변경
	@Override
	public void profileUpdate(User cUser) {
		String sql = "update userinfo set PROFILE = ? where user_idx= ?";
		
		System.out.println("UserDaoImpl : "+cUser.getProfile());
		
		//DB 객체
		PreparedStatement ps = null;

		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);

			ps.setString(1, cUser.getProfile());
			ps.setInt(2, cUser.getUser_idx());

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

	// 임시비번으로 비번 변경 
	@Override
	public void changeTempPw(String email, String tempPw) {
		String sql = "UPDATE userinfo SET PASSWORD = ? where id = ?";
		
		// DB 객체
		PreparedStatement ps = null;

		try {
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, tempPw);
			ps.setString(2, email);

			ps.executeUpdate();

			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null)	ps.close();
						
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
					
		
	}




}
