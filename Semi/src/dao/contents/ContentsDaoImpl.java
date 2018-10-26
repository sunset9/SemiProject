package dao.contents;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.plan.Plan;
import utils.DBConn;

public class ContentsDaoImpl implements ContentsDao{
	private Connection conn = DBConn.getConnection();
	
	//리스트 검색하기
	@Override
	public List<Plan> selectList(String category, String searchValue) {
		
		String sql = "";
		if(category != null && searchValue != null) {
			sql = "SELECT * FROM PLANNER WHERE ? LIKE %2%";
		}
		
		//DB 객체
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Plan> list = null;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, category);
			ps.setString(2, searchValue);
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

}
