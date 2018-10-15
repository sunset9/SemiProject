package service.timetable;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.timetable.TimetableDao;
import dto.timetable.Location;
import dto.plan.Plan;
import dto.timetable.Timetable;

public interface TimetableService {
	
	// 요청 파라미터 추출
	Map<Timetable, Location> getParam(HttpServletRequest req);
	
	// Timetable 리스트 가져오기
	List<Timetable> getTimetableList(Plan plan);
	
	// Location 리스트 가져오기
	List<Location> getLocationList(Plan plan);
	
	// 특정 Timetable이 시작하는 날짜의 모든 Location 리스트 가져오기
	List<Location> getLocatioinList(Timetable timetable);
	
	// plan_idx와 loc_idx까지 set된 타임테이블 객체 리스트 반환
	List<Timetable> getCompletedTimetable(Plan plan, Map<Timetable, Location> ttLoc);
	
	// 타임테이블 정보 저장하기
	void write(Plan plan, Map<Timetable, Location> ttLoc);
	
	// 타임테이블 수정 정보 저장하기
	void update(Plan plan, Map<Timetable, Location> ttLoc);
	
	// 타임테이블 정보 삭제하기
	void delete(Plan plan);
}
