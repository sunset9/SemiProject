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
		System.out.println(category);
		System.out.println(searchValue);
		
		String sql = "SELECT * FROM PLANNER WHERE ? LIKE '%' || ? || '%'";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Plan> planList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, category);
			ps.setString(2, searchValue);
			
			rs = ps.executeQuery();
			
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
				plan.setBannerURL(rs.getString("BANNERURL"));
				
				System.out.println("ContentsDaoImpl plan : "+plan);
				
				planList.add(plan);
				
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
		return planList;
	}

}
