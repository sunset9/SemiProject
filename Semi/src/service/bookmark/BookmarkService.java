package service.bookmark;

import dto.bookmark.Bookmark;
import dto.plan.Plan;

public interface BookmarkService {
	void insertBookmark(Plan plan);
	
	void deleteBookmark(Plan plan);
	
	Bookmark getBookmarkInfo(Plan plan);
}
