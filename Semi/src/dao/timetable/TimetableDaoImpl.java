package dao.timetable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
		List<Timetable> listRes = new ArrayList<>();
		String sql = "SELECT ttb_idx, plan_idx, loc_idx, start_time, end_time FROM timetable"
				+ " WHERE plan_idx = ?"
				+ " ORDER BY start_time";
		
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
		
		System.out.println(listRes);
		return listRes;
	}
	
	// plan_idx에 해당하는 모든 Timetable의 Location 정보 조회
	public List<Location> selectLocationList(Plan plan) {
		List<Location> listRes = new ArrayList<>();
		String sql = "SELECT l.loc_idx, l.place_name, l.lat, l.lng, l.address, l.photo_url, l.place_id FROM location l"
				+ " RIGHT JOIN timetable t"
				+ " ON t.loc_idx = l.loc_idx"
				+ " WHERE t.plan_idx = ?"
				+ " ORDER BY ttb_idx";
		
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
				loc.setPhoto_url(rs.getString("photo_url"));
				loc.setPlace_id(rs.getString("place_id"));
				
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

	// 타임테이블 삽입
	public void insertTimetable(Timetable ttb) {
		String sql = "INSERT INTO timetable(ttb_idx, plan_idx, loc_idx, start_time, end_time)"
				+ " VALUES(?, ?, ?"
				+ ", TO_DATE(?, 'yyyy/mm/dd hh24:mi')"
				+ ", TO_DATE(?, 'yyyy/mm/dd hh24:mi')"
				+ " )";

		try {
			// 오토커밋 해제
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, ttb.getTtb_idx());
			ps.setInt(2, ttb.getPlan_idx());
			ps.setInt(3, ttb.getLoc_idx());
			ps.setString(4, new SimpleDateFormat("yyyy-MM-dd HH:mm").format(ttb.getStart_time()));
			ps.setString(5, new SimpleDateFormat("yyyy-MM-dd HH:mm").format(ttb.getEnd_time()));
			
			ps.executeUpdate();
			
			conn.commit();
			
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
	

	@Override
	public void updateTimetable(Timetable ttb) {
		String sql = "INSERT INTO timetable(ttb_idx, plan_idx, loc_idx, start_time, end_time)"
				+ " VALUES(?, ?, ?"
				+ ", TO_DATE(?, 'yyyy/mm/dd hh24:mi')"
				+ ", TO_DATE(?, 'yyyy/mm/dd hh24:mi')"
				+ " )";

		try {
			// 오토커밋 해제
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, ttb.getTtb_idx());
			ps.setInt(2, ttb.getPlan_idx());
			ps.setInt(3, ttb.getLoc_idx());
			ps.setString(4, new SimpleDateFormat("yyyy-MM-dd HH:mm").format(ttb.getStart_time()));
			ps.setString(5, new SimpleDateFormat("yyyy-MM-dd HH:mm").format(ttb.getEnd_time()));
			
			ps.executeUpdate();
			
			conn.commit();
			
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

	// 위치 정보 삽입
	public void insertLocation(Location loc) {
		String sql = "INSERT INTO location(loc_idx, place_name, lat, lng, address, photo_url, place_id)"
				+ " VALUES (location_seq.nextval, ?, ?, ?, ?, ?, ?)";
		
		try {
			// 오토커밋 해제
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(sql);
			ps.setString(1, loc.getPlace_name());
			ps.setDouble(2, loc.getLat());
			ps.setDouble(3, loc.getLng());
			ps.setString(4, loc.getAddress());
			ps.setString(5, loc.getPhoto_url());
			ps.setString(6, loc.getPlace_id());
			
			ps.executeUpdate();
			
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
	}
	
	// 만약 place_id 로 저장되어있는 위치정보가 있다면 해당 위치정보 컬럼의 idx반환
	public int selectLocationIdx(Location location) {
		int idx = -1;
		String sql = "SELECT loc_idx FROM location"
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
		String sql = "DELETE timetable"
				+ " WHERE plan_idx=?";
		
		try {
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, plan.getPlan_idx());
			
			ps.executeUpdate();
			
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
		} finally {
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

	@Override
	public int selectTtbIdx() {
		String sql = "SELECT timetable_seq.nextval FROM dual";
		int seq = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				seq = rs.getInt(1);
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
		return seq;
	}
}
