package dto.user;

import java.util.Date;

public class Bookmark {

	private String title;
	private String bannerURL;
	
	@Override
	public String toString() {
		return "Bookmark [title=" + title + ", bannerURL=" + bannerURL + "]";
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
