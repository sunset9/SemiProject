package dao.timetable;

import java.util.List;

import dto.timetable.Location;
import dto.plan.Plan;
import dto.timetable.Timetable;

public interface TimetableDao {
	
	// 해당 plan에 속해있는 모든 Timetable 조회 
	List<Timetable> selectTimetableList(Plan plan);
	
	// 해당 plan에 속해있는 모든 Location 정보 조회
	List<Location> selectLocationList(Plan plan);

	// 특정 Timetable의 start_date와 같은 Timetable들의 Location 정보 조회
	List<Location> selectLocationListByStartDate(Timetable timetable);
	
	// 타임테이블 List 삽입
	void insertTimetable(Timetable timetable);
	
	// 위치정보 삽입
	void insertLocation(Location location);
	
	// 이미 저장되어 있는 location이 있으면, 해당 loc_idx 반환
	int selectLocationIdx(Location location);
	
	// 타임테이블 업데이트
	void updateTimetable(Timetable ttb);
	
	// 타임테이블 삭제
	void deleteTimetableListByPlanIdx(Plan plan);
	
	// 타임테이블 넘버로 스토리 있는지 없는지 유무 true:스토리 있음 false:스토리 없음
	Boolean selectIsStoryByTimetableIdx(Timetable timetable);
	
	// 타임테이블 일차 구하기
	int selectDay(Timetable timetable);
	
	String selectPlacenameByTimetableIdx(Timetable timetable);

	// timetable sequence next값 조회
	int selectTtbIdx();

}
