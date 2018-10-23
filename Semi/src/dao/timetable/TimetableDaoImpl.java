package dao.timetable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.plan.Plan;
import dto.timetable.Location;
import dto.timetable.Timetable;
import utils.DBConn;

public class TimetableDaoImpl implements TimetableDao{
	private Connection conn = DBConn.getConnection();
	private PreparedStatement ps;
	private ResultSet rs;
	
	// plan_idx에 해당하는 모든 Timetable 조회 
	public List<Timetable> selectTimetableList(Plan plan) {
		String sql = "SELECT ttb_idx, plan_idx, loc_idx, start_time, end_time FROM timetable"
				+ " WHERE plan_idx = ?"
				+ " ORDER BY ttb_idx";
		List<Timetable> listRes = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, plan.getPlan_idx());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Timetable ttb = new Timetable();
				
				ttb.setTtb_idx(rs.getInt("ttb_idx"));
				ttb.setPlan_idx(rs.getInt("plan_idx"));
				ttb.setLoc_idx(rs.getInt("loc_idx"));
				ttb.setStart_time(new Date(rs.getTimestamp("start_time").getTime()));
				ttb.setEnd_time(new Date(rs.getTimestamp("end_time").getTime()));
				
				listRes.add(ttb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null) ps.close();
				if(rs!=null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listRes;
	}
	
	// plan_idx에 해당하는 모든 Timetable의 Location 정보 조회
	public List<Location> selectLocationList(Plan plan) {
		String sql = "SELECT l.loc_idx, l.place_name, l.lat, l.lng, l.address, l.create_date FROM location l"
				+ " RIGHT JOIN timetable t"
				+ " ON t.loc_idx = l.loc_idx"
				+ " WHERE t.plan_idx = ?"
				+ " ORDER BY ttb_idx";
		List<Location> listRes = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, plan.getPlan_idx());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Location loc = new Location();
				
				loc.setLoc_idx(rs.getInt("loc_idx"));
				loc.setPlace_name(rs.getString("place_name"));
				loc.setLat(rs.getFloat("lat"));
				loc.setLng(rs.getFloat("lng"));
				loc.setAddress(rs.getString("address"));
				
				listRes.add(loc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null) ps.close();
				if(rs!=null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listRes;
	}

	// 특정 Timetable의 start_date와 같은 Timetable들의 Location 정보 조회
	public List<Location> selectLocationListByStartDate(Timetable timetable) {
		return null;
	}

	// 타임테이블 List - 반복문 돌면서 저장
	public void insertTimetable(List<Timetable> timetableList) {
		
	}
	
	// 위치 정보 insert
	public void insertLocation(Location location) {
	}
	
	// 만약 이미 같은 위도, 경도로 저장되어있는 위치정보가 있다면 해당 위치정보 컬럼의 idx반환
	public int selectLocationIdx(Location location) {
		return 0;
	}
	
	// 타임테이블 삭제
	public void deleteTimetable(Plan plan) {
		
	}

	// 타임테이블 넘버로 스토리 있는지 없는지 유무 
	public Boolean selectIsStoryByTimetableIdx(Timetable timetable) {
		String sql = "SELECT" + 
				" count(1) cnt" + 
				" FROM" + 
				" story s" + 
				" RIGHT JOIN timetable ttb ON s.ttb_idx = ttb.ttb_idx" + 
				" WHERE" + 
				" ttb.ttb_idx = ? AND s.ttb_idx IS NULL";
		Boolean Is = null;
		
		try {
			int cnt = 0;
			ps = conn.prepareStatement(sql);
			ps.setInt(1, timetable.getTtb_idx());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if (cnt>0) {Is = false;}
			else {Is = true;}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null) ps.close();
				if(rs!=null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		
		return Is;
	}

	// 타임테이블 일차 구하기
	public int selectDay(Timetable timetable) {
		
		String sql = "SELECT" + 
				" trunc( (ttb.start_time - p.start_date) + 0.9,0) AS diffDay" + 
				" FROM" + 
				" timetable ttb" + 
				" LEFT JOIN planner p ON ttb.plan_idx = p.plan_idx" + 
				" where" + 
				" ttb.ttb_idx = ?" + 
				" ORDER BY" + 
				" ttb.start_time";
		
		int Day=0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, timetable.getTtb_idx());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				Day = rs.getInt("diffDay");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null) ps.close();
				if(rs!=null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return Day;
	}

	@Override
	public String selectPlacenameByTimetableIdx(Timetable timetable) {
		String sql = "SELECT" + 
				" loc.place_name name" + 
				" FROM" + 
				" location loc" + 
				" LEFT JOIN timetable ttb ON ttb.loc_idx = loc.loc_idx" + 
				" WHERE" + 
				" ttb_idx =?";
		
		String place_name = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, timetable.getTtb_idx());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				place_name =  rs.getString("name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null) ps.close();
				if(rs!=null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return place_name;
	}
}
