package dto.user;

public class Search {
	private String category;
	private String searchValue;
	@Override
	public String toString() {
		return "search [category=" + category + ", searchValue=" + searchValue + "]";
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	
	
}
