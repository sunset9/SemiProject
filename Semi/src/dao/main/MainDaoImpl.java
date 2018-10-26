package dao.main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import dto.plan.Plan;
import dto.user.User;
import utils.DBConn;

public class MainDaoImpl implements MainDao{
	private Connection conn = DBConn.getConnection();

	
	//추천 콘텐츠 
	@Override
	public List getRecommendedContents() {
		return null;
	}

	//최신 콘텐츠
	@Override
	public List getNewestContents() {
		return null;
	}
	
	
	//새 일정 만들기 
	@Override
	public void insertPlan(Plan param, User user) {
		//INSERT INTO PLANNER(plan_idx,user_idx,start_date,end_date,title,traveled,opened,distance,bannerurl) 
		//VALUES(planner_seq.nextval,71,to_date('20181010','yyyy-mm-dd'),to_date('20181013','yyyy-mm-dd'),'testPlan',1,1,20,'/upload/user/paris.jpg');

		//System.out.println("메인디에이오 : "+param.getStart_date());
		//System.out.println("메인디에이오 : "+param.getEnd_date());
		String sql = "";
		sql += "INSERT INTO PLANNER(plan_idx, user_idx, start_date, end_date, title, traveled, opened, distance, bannerurl)";
		sql += " VALUES (planner_seq.nextval, ?, to_date(?, 'yyyy-MM-dd'), to_date(?, 'yyyy-MM-dd'), ?, ?, 0, 0, '/upload/user/paris.jpg')";
		
		PreparedStatement ps = null;
		
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, user.getUser_idx());
			
			DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy",Locale.ENGLISH);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd",Locale.KOREA);
			
			String sD = sdf.format(param.getStart_date());
			System.out.println("dd : "+sD);
			ps.setString(2, sD);
			
			String eD = sdf.format(param.getEnd_date());
			System.out.println("dd : "+eD);
			ps.setString(3, eD);
			
			ps.setString(4, param.getTitle());
			ps.setInt(5, param.getTraveled());
			
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

}
