package service.bookmark;

import dto.user.Bookmark;
import dto.plan.Plan;

public interface BookmarkService {
	void insertBookmark(Plan plan, int user_idx);
	
	void deleteBookmark(int user_idx);
	
	Bookmark getBookmarkInfo(Plan plan);
}
