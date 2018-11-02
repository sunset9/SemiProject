package dto.user;

import java.util.Date;

public class Bookmark {

	private int book_idx;
	private int user_idx;
	private int plan_idx;
	private String writeNick;
	private String title;
	private String bannerURL;
	
	
	public String getWriteNick() {
		return writeNick;
	}
	public void setWriteNick(String writeNick) {
		this.writeNick = writeNick;
	}
	@Override
	public String toString() {
		return "Bookmark [book_idx=" + book_idx + ", user_idx=" + user_idx + ", plan_idx=" + plan_idx + ", writeNick="
				+ writeNick + ", title=" + title + ", bannerURL=" + bannerURL + "]";
	}
	public int getBook_idx() {
		return book_idx;
	}
	public void setBook_idx(int book_idx) {
		this.book_idx = book_idx;
	}
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public int getPlan_idx() {
		return plan_idx;
	}
	public void setPlan_idx(int plan_idx) {
		this.plan_idx = plan_idx;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBannerURL() {
		return bannerURL;
	}
	public void setBannerURL(String bannerURL) {
		this.bannerURL = bannerURL;
	}
	

	
	
	
	
}
