package service.timetable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oreilly.servlet.MultipartRequest;

import dao.timetable.TimetableDao;
import dao.timetable.TimetableDaoImpl;
import dto.plan.Plan;
import dto.timetable.Location;
import dto.timetable.Timetable;
import service.stroy.StoryService;
import service.stroy.StoryServiceImpl;

public class TimetableServiceImpl implements TimetableService{

	TimetableDao ttbDao = new TimetableDaoImpl();
	
	// 요청 파라미터 -> Timetable, Location 정보 추출 
		public Map<Timetable, Location> getParam(HttpServletRequest req) {
			Map<Timetable, Location> ttbLoc = new HashMap<>();
			List<Timetable> ttbList = new ArrayList<>();
			List<Location> locList = new ArrayList<>();
			
			// gson 객체생성
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm");
			Gson gson = gsonBuilder.create();
			
			// String 형태의 json 파라미터 얻기
			String events = req.getParameter("events");
			System.out.println("-----------events목록 (타임테이블 서비스)");
			System.out.println(events);
			
			if(events!=null & !"".equals(events)) {
				// JSON String -> Timetable DTO 배열
				Timetable[] ttbs = gson.fromJson(events, Timetable[].class);
				// Timetable 배열 -> 리스트로 변환 (loc_idx 비어있음)
				ttbList = Arrays.asList(ttbs);
				System.out.println("-----파싱한 ttb 목록---");
				System.out.println(ttbList);
				
				// JSON String -> Location DTO 배열
				Location[] locs = gson.fromJson(events, Location[].class);
				// Location 리스트
				locList = Arrays.asList(locs);
			}
			
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
		
	// 요청 파라미터 -> Timetable, Location 정보 추출 (저장용, multipart 타입)
	public Map<Timetable, Location> getParam(MultipartRequest mul) {
		Map<Timetable, Location> ttbLoc = new HashMap<>();
		List<Timetable> ttbList = new ArrayList<>();
		List<Location> locList = new ArrayList<>();
		
		// gson 객체생성
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm");
		Gson gson = gsonBuilder.create();
		
		// String 형태의 json 파라미터 얻기
		String events = mul.getParameter("events");
		System.out.println("-----------events목록 (타임테이블 서비스)");
		System.out.println(events);
		
		if(events!=null & !"".equals(events)) {
			// JSON String -> Timetable DTO 배열
			Timetable[] ttbs = gson.fromJson(events, Timetable[].class);
			// Timetable 배열 -> 리스트로 변환 (loc_idx 비어있음)
			ttbList = Arrays.asList(ttbs);
			System.out.println("-----파싱한 ttb 목록---");
			System.out.println(ttbList);
			
			// JSON String -> Location DTO 배열
			Location[] locs = gson.fromJson(events, Location[].class);
			// Location 리스트
			locList = Arrays.asList(locs);
		}
		
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
	
	@Override
	public Map<Timetable, Location> getMiniParam(HttpServletRequest req) {
		Map<Timetable, Location> ttbLoc = new HashMap<>();
		
		// gson 객체생성
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm");
		Gson gson = gsonBuilder.create();
		
		Timetable ttb = null;
		Location loc = null;
		
		// String 형태의 json 파라미터 얻기
		String ttbStr = req.getParameter("ttbJson");
		
		if(ttbStr!=null & !"".equals(ttbStr)) {
			// JSON String -> Timetable DTO 배열
			ttb = gson.fromJson(ttbStr, Timetable.class);
			loc = gson.fromJson(ttbStr, Location.class);
		}
		
		ttbLoc.put(ttb, loc);
		
		
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
			
			list.get(i).setIs_story(isStory(tb.getTtb_idx()));
			
			list.get(i).setDay(ttbDao.selectDay(tb));
			
			list.get(i).setPlace_name(ttbDao.selectPlacenameByTimetableIdx(tb));
		}
		
		return list;
	}
	
	public boolean isStory(int ttb_idx) {
		return ttbDao.selectIsStoryByTimetableIdx(ttb_idx);
	}
	
	// 해당 Plan의 모든 Location 리스트 가져오기
	public List<Location> getLocationList(Plan plan, List<Timetable> ttbList){
		List<Location> locList = new ArrayList<>();
		
		for(Timetable ttb : ttbList) {
			locList.add(ttbDao.selectLocationList(plan, ttb));
		}
		
		return locList;
	}
	
	// 특정 Timetable이 시작하는 날짜의 모든 Location 리스트 가져오기
	// 사용하는 곳 -> 타임테이블에 일정 Drop/ Delete 시 지도에 새로 그려줘야 한다.
	public List<Location> getLocatioinList(Timetable timetable){
		return ttbDao.selectLocationListByStartDate(timetable);
	}
	
	// loc_idx까지 set된 타임테이블 객체 리스트 반환
	public List<Timetable> getCompletedTimetable(Map<Timetable, Location> ttbLoc){
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
			
			// loc_idx 삽입
			ttb.setLoc_idx(loc_idx);
			
			// List에 완성된 타임테이블 객체들 넣어줌
			ttbList.add(ttb);
		}

		return ttbList;
	}
	
	// 타임테이블 정보 저장하기(수정), 업데이트한 타임테이블 목록 반환
	public void update(Plan plan, Map<Timetable, Location> ttbLoc) {
		List<Timetable> ttbList = getCompletedTimetable(ttbLoc);
		
//		System.out.println(ttbList);
		
		// 넘어온 리스트에 없는 타임테이블들 삭제
		deleteTimetableList(plan, ttbList);
		
		for(Timetable ttb: ttbList) {
			// 새로 받은 타임테이블 (ttb_idx = 음수) 저장
			if(ttb.getTtb_idx()<0) { // 새로 추가된 타임테이블은 insert
				ttb.setTtb_idx(getTtbIdx());
				ttbDao.insertTimetable(ttb);
			} else {
				ttbDao.updateTimetable(ttb);
			}
		}
		
	}

	// 타임테이블 저장 (미니뷰로 저장한 경우)
	@Override
	public void writeTtb(int ttb_idx, Map<Timetable, Location> ttbLoc) {
		List<Timetable> ttbList= getCompletedTimetable(ttbLoc);
		
		Timetable ttb = ttbList.get(0);
		ttb.setTtb_idx(ttb_idx);
		
		ttbDao.insertTimetable(ttb);
	}

	// 타임테이블 정보 삭제하기
	@Override
	public void deleteTimetable(Plan plan) {
		ttbDao.deleteTimetableListByPlanIdx(plan);
	}
	
	
	@Override
	public void deleteTimetableList(Plan plan, List<Timetable> ttbList) {
		ttbDao.deleteTimetableList(plan,ttbList);
		
	}

	public int getTtbIdx() {
		return ttbDao.selectTtbIdx();
	}

	@Override
	public int getMiniTtbIdx(Map<Timetable, Location> ttbLocParam) {
		Timetable ttb = ttbLocParam.keySet().iterator().next();
		int ttb_idx = ttb.getTtb_idx();
		
		if( ttb_idx < 0) {
			ttb_idx = getTtbIdx();
		}
		
		return ttb_idx;
	}


}
