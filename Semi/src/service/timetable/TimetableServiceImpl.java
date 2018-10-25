package service.timetable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.timetable.TimetableDao;
import dao.timetable.TimetableDaoImpl;
import dto.plan.Plan;
import dto.timetable.Location;
import dto.timetable.Timetable;

public class TimetableServiceImpl implements TimetableService{

	TimetableDao ttbDao = new TimetableDaoImpl();
	
	// 요청 파라미터 -> Timetable, Location 정보 추출
	public Map<Timetable, Location> getParam(HttpServletRequest req) {
		Map<Timetable, Location> ttbLoc = new HashMap<>();
		
		// gson 객체생성
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm");
		Gson gson = gsonBuilder.create();
		
		// String 형태의 json 파라미터 얻기
		String events = req.getParameter("events");
		
		// JSON String -> Timetable DTO 배열
		Timetable[] ttbs = gson.fromJson(events, Timetable[].class);
		// Timetable 리스트 (plan_idx, loc_idx 비어있음)
		List<Timetable> ttbList = Arrays.asList(ttbs);
		
		// JSON String -> Location DTO 배열
		Location[] locs = gson.fromJson(events, Location[].class);
		// Location 리스트
		List<Location> locList = Arrays.asList(locs);
		
		// Timetable 과  Location 리스트의 개수가 같아야함
		if(ttbList.size() != locList.size()) {
			System.out.println("[ERR] 파라미터 추출에 실패하였습니다.");
			return null;
		}
		
		// Map형식으로 Timetable과 Location 정보 저장
		for(int i = 0; i<ttbList.size(); i++) {
			ttbLoc.put(ttbList.get(i), locList.get(i));
		}
		
		return ttbLoc;
	}
	
	// Timetable 리스트 가져오기
	public List<Timetable> getTimetableList(Plan plan){
		
		
		List<Timetable> list = new ArrayList<>();
		
		list = ttbDao.selectTimetableList(plan);
		
		//일차와 스토리유무 list에 넣어주기
		for(int i = 0; i<list.size(); i++) {
			Timetable tb = new Timetable();
			
			tb.setTtb_idx(list.get(i).getTtb_idx());
			
			list.get(i).setIs_story(ttbDao.selectIsStoryByTimetableIdx(tb));
			
			list.get(i).setDay(ttbDao.selectDay(tb));
			
			list.get(i).setPlace_name(ttbDao.selectPlacenameByTimetableIdx(tb));
		}
		
		return list;
	}
	
	
	// 해당 Plan의 모든 Location 리스트 가져오기
	public List<Location> getLocationList(Plan plan){
		return ttbDao.selectLocationList(plan);
	}
	
	// 특정 Timetable이 시작하는 날짜의 모든 Location 리스트 가져오기
	// 사용하는 곳 -> 타임테이블에 일정 Drop/ Delete 시 지도에 새로 그려줘야 한다.
	public List<Location> getLocatioinList(Timetable timetable){
		return ttbDao.selectLocationListByStartDate(timetable);
	}
	
	// plan_idx와 loc_idx까지 set된 타임테이블 객체 리스트 반환
	public List<Timetable> getCompletedTimetable(Plan plan, Map<Timetable, Location> ttbLoc){
		// Map 반복문 돌면서 loc_idx 삽입해주기
		List<Timetable> ttbList = new ArrayList<>();
		
		// Map에 대한 반복문
		// 완성된 타임테이블 객체 리스트에 삽입
		for(Timetable ttb: ttbLoc.keySet()) {
			// 해당 타임테이블이 가지고 있는 위치정보에 대한 loc_idx 조회  
			int loc_idx = ttbDao.selectLocationIdx(ttbLoc.get(ttb));
			// 이미 존재하는 location 존재 유무 확인
			if(loc_idx < 0) {  // 존재하지 않으면 -1 반환
				// 기존에 저장된 위치 정보가 없으면 Location 삽입
				ttbDao.insertLocation(ttbLoc.get(ttb));
				// 삽입 후 loc_idx 다시 조회
				loc_idx = ttbDao.selectLocationIdx(ttbLoc.get(ttb));
			}
			
			// plan_idx 삽입
			ttb.setPlan_idx(plan.getPlan_idx());
			// loc_idx 삽입
			ttb.setLoc_idx(loc_idx);
			
			// List에 완성된 타임테이블 객체들 넣어줌
			ttbList.add(ttb);
		}

		return ttbList;
	}
	
	// 타임테이블 정보 저장하기
	public void write(Plan plan, Map<Timetable, Location> ttbLoc) {
		List<Timetable> ttList= getCompletedTimetable(plan, ttbLoc);
		
//		ttbDao.insertTimetable(ttList);
	}
	
	// 타임테이블 정보 저장하기(수정)
	public void update(Plan plan, Map<Timetable, Location> ttbLoc) {
		List<Timetable> ttbList= getCompletedTimetable(plan, ttbLoc);
		
//		System.out.println(ttbList);
		
		// 기존에 있던 타임테이블 모두 삭제
		ttbDao.deleteTimetable(plan);
		
		for(Timetable ttb: ttbList) {
			// 새로 받은 타임테이블 저장
			if(ttb.getTtb_idx()<0) { // 새로 추가된 타임테이블은 insert
				ttbDao.insertTimetable(ttb);
			} else {
				ttbDao.updateTimetable(ttb);
			}
			
		}
	}

	// 타임테이블 정보 삭제하기
	public void delete(Plan plan) {
		
	}
}
