package dto.timetable;

import java.util.Date;

public class Location {
//	장소 pk
	private int loc_idx;
//	장소 이름
	private String place_name;
//	위도
	private double lat;
//	경도
	private double lng;
//	장소 주소
	private String address;
	
	private String photo_url;
	
	private String place_id;
	
	@Override
	public String toString() {
		return "Location [loc_idx=" + loc_idx + ", place_name=" + place_name + ", lat=" + lat + ", lng=" + lng
				+ ", address=" + address + ", photo_url=" + photo_url + ", place_id=" + place_id + "]";
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

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
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

	

}
