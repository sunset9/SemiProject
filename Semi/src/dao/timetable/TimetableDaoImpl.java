package dao.timetable;

import java.util.List;

import dto.plan.Plan;
import dto.timetable.Location;
import dto.timetable.Timetable;

public class TimetableDaoImpl implements TimetableDao{

	// plan_idx에 해당하는 모든 Timetable 조회 
	public List<Timetable> selectTimetableList(Plan plan) {
		return null;
	}
	
	// plan_idx에 해당하는 모든 Timetable의 Location 정보 조회
	public List<Location> selectLocationList(Plan plan) {
		return null;
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
}
