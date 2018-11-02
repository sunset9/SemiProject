package service.bookmark;

import dto.user.Bookmark;
import dto.plan.Plan;

public interface BookmarkService {
	
	// bookmark 추가 하기 
	void insertBookmark(Plan plan, int user_idx);
	
	void deleteBookmark(Plan plan);
	
	Bookmark getBookmarkInfo(Plan plan);
}
