package service.bookmark;

import dto.user.Bookmark;

import javax.servlet.http.HttpServletRequest;

import dto.plan.Plan;

public interface BookmarkService {
	void insertBookmark(Plan plan, int user_idx);
	
	void deleteBookmark(Bookmark book);
	
	Bookmark getBookmarkInfo(Plan plan);
	
	//파라미터 얻어오기 
	public Bookmark getParam (HttpServletRequest req);
}
