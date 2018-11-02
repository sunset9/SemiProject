package dao.bookmark;

import dto.bookmark.Bookmark;
import dto.plan.Plan;

public interface BookmarkDao {
	void insertBookmarkByPlanIdx(Plan plan, int user_idx);
	void deleteBookmarkByPlanIdx(int user_idx);
	Bookmark selectBookmarkByPlanIdx(Plan plan);
}
