package service.timetable;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.timetable.TimetableDao;
import dto.timetable.Location;
import dto.plan.Plan;
import dto.story.Story;
import dto.timetable.Timetable;

public interface TimetableService {
	
	// 요청 파라미터 추출
	Map<Timetable, Location> getParam(HttpServletRequest req);
	
	// 미니뷰용 파라미터 추출(저장하려는 미니뷰에 해당하는 타임테이블 하나)
	Map<Timetable, Location> getMiniParam(HttpServletRequest req);
	
	// Timetable 리스트 가져오기
	List<Timetable> getTimetableList(Plan plan);
	
	// Location 리스트 가져오기
	List<Location> getLocationList(Plan plan, List<Timetable> ttbList);
	
	boolean isStory(int ttb_idx);
	
	// 특정 Timetable이 시작하는 날짜의 모든 Location 리스트 가져오기
	List<Location> getLocatioinList(Timetable timetable);
	
	// loc_idx까지 set된 타임테이블 객체 리스트 반환
	List<Timetable> getCompletedTimetable(Map<Timetable, Location> ttbLoc);
	
	// 타임테이블 수정 정보 저장하기
	void update(Plan plan, Map<Timetable, Location> ttbLoc);
	
	// 새로운 타임테이블 저장 ( 미니뷰용, 저장하려는 미니뷰스토리에 해당하는 타임테이블 하나만 저장)
	void writeTtb(int ttb_idx, Map<Timetable, Location> ttbLoc);
	
	// 타임테이블 정보 삭제하기
	boolean delete(Plan plan);
	
	// ttb_idx next 값 가져오기
	int getTtbIdx();

	int getMiniTtbIdx(Map<Timetable, Location> ttbLocParam);


	
}
