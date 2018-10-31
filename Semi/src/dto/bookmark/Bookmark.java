package dto.bookmark;

public class Bookmark {
	private int book_idx;
	private int user_idx;
	private int plan_idx;
	
	@Override
	public String toString() {
		return "Bookmark [ book_idx = " + book_idx + ", user_idx = "
				+ user_idx + ", plan_idx = " + plan_idx + "]"; 
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
}
