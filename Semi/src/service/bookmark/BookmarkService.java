package service.bookmark;

import dto.bookmark.Bookmark;
import dto.plan.Plan;

public interface BookmarkService {
	void insertBookmark(Plan plan, int user_idx);
	
	void deleteBookmark(int user_idx);
	
	Bookmark getBookmarkInfo(Plan plan);
}
