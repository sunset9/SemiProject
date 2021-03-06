package dto.story;

import java.util.Date;
import java.util.Map;

public class Story {
	// 스토리 인덱스
	private int story_idx;
	// 플랜 인덱스
	private int plan_idx;
	// 타임 테이블 인덱스
	private int ttb_idx;
	// 유저 인덱스
	private int user_idx;
	// 스토리 본문 내용
	private String content;
	// 스토리 작성일
	private Date create_date;
		
	//현재 여행 날짜
	private String travel_day;
	
	private String start_time;
	// 타임테이블 끝 시간
	private String end_time;
	// 장소 이름
	private String place_name;
	//몇일차인지
	private int calcDay;
	
	private int CommCnt;
	
	private int accCnt;
	
	@Override
	public String toString() {
		return "Story [story_idx=" + story_idx + ", plan_idx=" + plan_idx + ", ttb_idx=" + ttb_idx + ", user_idx="
				+ user_idx + ", content=" + content + ", create_date=" + create_date + ", travel_day=" + travel_day
				+ ", start_time=" + start_time + ", end_time=" + end_time + ", place_name=" + place_name + ", calcDay="
				+ calcDay + ", CommCnt=" + CommCnt + ", accCnt=" + accCnt + "]";
	}
	public int getAccCnt() {
		return accCnt;
	}
	public void setAccCnt(int accCnt) {
		this.accCnt = accCnt;
	}
	public int getCommCnt() {
		return CommCnt;
	}
	public void setCommCnt(int commCnt) {
		CommCnt = commCnt;
	}
	public int getCalcDay() {
		return calcDay;
	}
	public void setCalcDay(int calcDay) {
		this.calcDay = calcDay;
	}
	public String getTravel_day() {
		return travel_day;
	}
	public void setTravel_day(String travel_day) {
		this.travel_day = travel_day;
	}


	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getPlace_name() {
		return place_name;
	}
	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}
	public int getStory_idx() {
		return story_idx;
	}
	public void setStory_idx(int story_idx) {
		this.story_idx = story_idx;
	}
	public int getPlan_idx() {
		return plan_idx;
	}
	public void setPlan_idx(int plan_idx) {
		this.plan_idx = plan_idx;
	}
	public int getTtb_idx() {
		return ttb_idx;
	}
	public void setTtb_idx(int ttb_idx) {
		this.ttb_idx = ttb_idx;
	}
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	
	
}
