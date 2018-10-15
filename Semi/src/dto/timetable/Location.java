package dto.timetable;

import java.util.Date;

public class Location {
//	장소 pk
	private int loc_idx;
//	장소 이름
	private String place_name;
//	위도
	private float lat;
//	경도
	private float lon;
//	장소 주소
	private String address;
	
	private Date start_date; // DB테이블엔 없는 컬럼, Timetable에 있는 정보
}
