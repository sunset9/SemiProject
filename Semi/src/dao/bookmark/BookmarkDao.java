package dao.bookmark;

import dto.user.Bookmark;
import dto.plan.Plan;

public interface BookmarkDao {
	
	// 북마크 추가
	void insertBookmarkByPlanIdx(Plan plan, int user_idx);
	
	void deleteBookmarkByPlanIdx(Plan plan);
	
	Bookmark selectBookmarkByPlanIdx(Plan plan);
}
