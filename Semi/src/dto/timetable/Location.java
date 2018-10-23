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
	private float lng;
//	장소 주소
	private String address;
	
	private String photo_url;
	
	private String place_id;
	
	private Date start_time; // DB테이블엔 없는 컬럼, Timetable에 있는 정보
	
	private Date end_time; // DB테이블엔 없는 컬럼, Timetable에 있는 정보

	@Override
	public String toString() {
		return "Location [loc_idx=" + loc_idx + ", place_name=" + place_name + ", lat=" + lat + ", lng=" + lng
				+ ", address=" + address + ", photo_url=" + photo_url + ", place_id=" + place_id + ", start_time="
				+ start_time + ", end_time=" + end_time + "]";
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

	public float getLng() {
		return lng;
	}

	public void setLng(float lng) {
		this.lng = lng;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoto_url() {
		return photo_url;
	}

	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}

	public String getPlace_id() {
		return place_id;
	}

	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	
	
	

}
