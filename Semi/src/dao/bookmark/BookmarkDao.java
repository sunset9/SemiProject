package dao.bookmark;

import dto.bookmark.Bookmark;
import dto.plan.Plan;

public interface BookmarkDao {
	void insertBookmarkByPlanIdx(Plan plan);
	void deleteBookmarkByPlanIdx(Plan plan);
	Bookmark selectBookmarkByPlanIdx(Plan plan);
}
