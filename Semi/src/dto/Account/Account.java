package dto.Account;

import java.util.Date;
import java.util.Map;

public class Account {
//	가계부 pk
	private int acc_idx;
//	일정메인 pk
	private int plan_idx;
//	스토리 pk
	private int story_idx;
	// 통화구분
	private int curr_idx; 

	//가계부 분류 카테고리(숙박 등)
	private int category;
	//입력비용 
	private double origin_cost;
	//환율적용비용
	private double caled_cost;
//	작성일
	private Date create_date;
	//통화이름
	private String curr_idx_name;

	//카테고리명
	private String category_name;
	
	private int ttb_idx;

	@Override
	public String toString() {
		return "Account [acc_idx=" + acc_idx + ", plan_idx=" + plan_idx + ", story_idx=" + story_idx + ", curr_idx="
				+ curr_idx + ", category=" + category + ", origin_cost=" + origin_cost + ", caled_cost=" + caled_cost
				+ ", create_date=" + create_date + ", curr_idx_name=" + curr_idx_name + ", category_name="
				+ category_name + ", ttb_idx=" + ttb_idx + "]";
	}
	
	public double getOrigin_cost() {
		return origin_cost;
	}
	public void setOrigin_cost(Double origin_cost) {
		this.origin_cost = origin_cost;
	}
	public double getCaled_cost() {
		return caled_cost;
	}
	public void setCaled_cost(Double caled_cost) {
		this.caled_cost = caled_cost;
	}
	public int getTtb_idx() {
		return ttb_idx;
	}
	public void setTtb_idx(int ttb_idx) {
		this.ttb_idx = ttb_idx;
	}
	
	
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	
	public String getCurr_idx_name() {
		return curr_idx_name;
	}
	public void setCurr_idx_name(String curr_idx_name) {
		this.curr_idx_name = curr_idx_name;
	}
	
	public int getCurr_idx() {
		return curr_idx;
	}
	public void setCurr_idx(int curr_idx) {
		this.curr_idx = curr_idx;
	}
	public int getCategory() {
		return category;
	}
	
	public void setCategory(int category) {
		this.category = category;
	}
	
	public int getAcc_idx() {
		return acc_idx;
	}
	public void setAcc_idx(int acc_idx) {
		this.acc_idx = acc_idx;
	}
	public int getPlan_idx() {
		return plan_idx;
	}
	public void setPlan_idx(int plan_idx) {
		this.plan_idx = plan_idx;
	}
	public int getStory_idx() {
		return story_idx;
	}
	public void setStory_idx(int story_idx) {
		this.story_idx = story_idx;
	}


	public Date getCreate_date() {
		return create_date;
	}


	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	 
	
	
	
	
}
