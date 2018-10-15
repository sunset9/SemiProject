package service.timetable;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.timetable.TimetableDao;
import dao.timetable.TimetableDaoImpl;
import dto.plan.Plan;
import dto.timetable.Location;
import dto.timetable.Timetable;

public class TimetableServiceImpl implements TimetableService{

	TimetableDao tDao = new TimetableDaoImpl();
	
	// 요청 파라미터 -> Timetable, Location 정보 추출
	public Map<Timetable, Location> getParam(HttpServletRequest req) {
		return null;
	}
	
	// Timetable 리스트 가져오기
	public List<Timetable> getTimetableList(Plan plan){ 
		return tDao.selectTimetableList(plan);
	}
	
	// 해당 Plan의 모든 Location 리스트 가져오기
	public List<Location> getLocationList(Plan plan){
		return tDao.selectLocationList(plan);
	}
	
	// 특정 Timetable이 시작하는 날짜의 모든 Location 리스트 가져오기
	// 사용하는 곳 -> 타임테이블에 일정 Drop/ Delete 시 지도에 새로 그려줘야 한다.
	public List<Location> getLocatioinList(Timetable timetable){
		return tDao.selectLocationListByStartDate(timetable);
	}
	
	// plan_idx와 loc_idx까지 set된 타임테이블 객체 리스트 반환
	public List<Timetable> getCompletedTimetable(Plan plan, Map<Timetable, Location> ttLoc){
		// Map 반복문 돌면서 loc_idx 삽입해주기
			// Location 객체 얻기 (= loc_idx 얻기) 
//			if(tDao.selectLocationIdx(new Location()).getIdx() == 0) {
//				// 일치하는 location이 없으면 삽입
//				tDao.insertLocation(Location);
//			}
//			// idx 값 받기
//			loc_idx = selectLocationIdx(new Location());
			
			// plan_idx, loc_idx set해주기
		
		return null;
	}
	
	// 타임테이블 정보 저장하기
	public void write(Plan plan, Map<Timetable, Location> ttLoc) {
		List<Timetable> ttList= getCompletedTimetable(plan, ttLoc);
		
		tDao.insertTimetable(ttList);
	}
	
	// 타임테이블 정보 저장하기(수정)
	public void update(Plan plan, Map<Timetable, Location> ttLoc) {
		List<Timetable> ttList= getCompletedTimetable(plan, ttLoc);

		//tDao.updateTimetable(ttList);
		 tDao.deleteTimetable(plan);
		 tDao.insertTimetable(ttList);
	}

	// 타임테이블 정보 삭제하기
	public void delete(Plan plan) {
		
	}
}
