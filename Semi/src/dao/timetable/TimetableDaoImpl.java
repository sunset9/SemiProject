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
	
	// 위치 정보 insert - 반복문 돌면서 저장
	public void insertLocation(List<Location> locationList) {
	}
	
	// 만약 place_id 로 저장되어있는 위치정보가 있다면 해당 위치정보 컬럼의 idx반환
	public int selectLocationIdx(Location location) {
		int idx = -1;
		String sql = "SELECT loc_idx FROM locaton"
				+ " WHERE place_id = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, location.getPlace_id());
			rs = ps.executeQuery();
			
			while(rs.next()) {
				idx = rs.getInt("loc_idx");
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
		
		return idx;
	}
	
	// 타임테이블 삭제
	public void deleteTimetable(Plan plan) {
		
	}
}
