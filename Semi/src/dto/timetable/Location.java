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
	private Date end_date; // DB테이블엔 없는 컬럼, Timetable에 있는 정보
	
	
	@Override
	public String toString() {
		return "Location [loc_idx=" + loc_idx + ", place_name=" + place_name + ", lat=" + lat + ", lon=" + lon
				+ ", address=" + address + ", start_date=" + start_date + ", end_date=" + end_date + "]";
	}
	public int getLoc_idx() {
		return loc_idx;
	}
	public void setLoc_idx(int loc_idx) {
		this.loc_idx = loc_idx;
	}
	public String getPlace_name() {
		return place_name;
	}
	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLon() {
		return lon;
	}
	public void setLon(float lon) {
		this.lon = lon;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	

}
