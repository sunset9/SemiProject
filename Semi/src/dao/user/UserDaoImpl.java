package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import utils.DBConn;
import dto.user.User;

public class UserDaoImpl implements UserDao{

	private Connection conn = DBConn.getConnection();
	
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
	public User selectUserAll() {
		// TODO Auto-generated method stub
		return null;
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

}
