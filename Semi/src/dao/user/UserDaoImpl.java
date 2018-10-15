package dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import dto.user.User;
import utils.DBConn;

public class UserDaoImpl implements UserDao{

	private Connection conn = DBConn.getConnection();
	
	//useremail & userpw로 유저 조회 
	//존재하는 유저면 1 반환
	@Override
	public int selecCntUserByUsereamilAndUserpw(User user) {
		
		String sql = "";
		sql += "SELECT COUNT(*) FROM userinfo";
		sql += " WHERE 1=1";
		if( user.getEmail() != null && user.getPassword() != null ) {
			sql += " AND email = ?";
			sql += " AND password = ?";
		}
		
		//DB 객체
		PreparedStatement ps = null; 
		ResultSet rs = null;
		
		int cnt = -1;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			if( user.getEmail() != null && user.getPassword() != null ) {
				ps.setString(1, user.getEmail());
				ps.setString(2, user.getPassword());
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

	//email로 유저 검색해서 유저 정보 가져오기
	@Override
	public User selectUserByEmail(User u) {
		
		String sql = "";
		sql += "SELECT * FROM userinfo";
		sql += " WHERE 1 = 1";
		if(u.getEmail() != null) {
			sql += " AND email = ?";
		}
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		User user = new User();
		
		try {
			ps = conn.prepareStatement(sql);
			if(u.getEmail() != null) {
				ps.setString(1, u.getEmail());
			}
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				user.setUser_idx(rs.getInt("user_idx"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setNickname(rs.getString("nickname"));
				user.setProfile(rs.getString("profile"));
				user.setGrade(rs.getString("grade"));
				user.setSns_type(rs.getString("sns_type"));
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

	//회원가입처리
	@Override
	public void insert(User user) {
		String sql = "";
		sql += "INSERT INTO userinfo(user_idx, email, password, nickname, profile, grade, sns_type, create_date)";
		sql += " VALUES(seq_user_idx.nextval, ?, ?, ?, 'a', '여행자', 'email', sysdate)"; 
		
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getNickname());
			
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

	//email로 조회 후 회원탈퇴처리
	@Override
	public void delete(User user) {
		String sql = "";
		sql += "DELETE userinfo";
		sql += " WHERE email = ?";
		
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getEmail());
			System.out.println(user.getEmail());
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
			sql += " WHERE password = ?";			
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
		sql += " WHERE email=?";
		
		PreparedStatement ps = null;
		
		try {			
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getNickname());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			
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
	public User selectUserByNick(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
