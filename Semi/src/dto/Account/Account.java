package dto.Account;

import java.util.Map;

public class Account {
//	가계부 pk
	private int acc_idx;
//	일정메인 pk
	private int plan_idx;
//	스토리 pk
	private int story_idx;
	// 통화구분
	private String sign; 
	//가계부 분류 카테고리(숙박 등)
	private String category;
	//입력비용 
	private float origin_cost;
	//환율적용비용
	private float caled_cost;
	
	
	@Override
	public String toString() {
		return "Account [acc_idx=" + acc_idx + ", plan_idx=" + plan_idx + ", story_idx=" + story_idx + ", sign=" + sign
				+ ", category=" + category + ", origin_cost=" + origin_cost + ", caled_cost=" + caled_cost + "]";
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
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public float getOrigin_cost() {
		return origin_cost;
	}
	public void setOrigin_cost(float origin_cost) {
		this.origin_cost = origin_cost;
	}
	public float getCaled_cost() {
		return caled_cost;
	}
	public void setCaled_cost(float caled_cost) {
		this.caled_cost = caled_cost;
	}
	
	 
	
	
	
	
}
