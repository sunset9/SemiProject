package dao.bookmark;

import dto.user.Bookmark;
import dto.plan.Plan;

public interface BookmarkDao {
	void insertBookmarkByPlanIdx(Plan plan, int user_idx);
	
	void deleteBookmark(Bookmark book);
	
	Bookmark selectBookmarkByPlanIdx(Plan plan);
}