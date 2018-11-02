package service.bookmark;

import dao.bookmark.BookmarkDao;
import dao.bookmark.BookmarkDaoImpl;
import dto.bookmark.Bookmark;
import dto.plan.Plan;

public class BookmarkServiceImpl implements BookmarkService{
	
	BookmarkDao bDao = new BookmarkDaoImpl();
	
	@Override
	public void insertBookmark(Plan plan, int user_idx) {
		bDao.insertBookmarkByPlanIdx(plan, user_idx);
	}
	
	@Override
	public void deleteBookmark(int user_idx) {
		bDao.deleteBookmarkByPlanIdx(user_idx);
	}
	
	@Override
	public Bookmark getBookmarkInfo(Plan plan) {
		return bDao.selectBookmarkByPlanIdx(plan);
	}
}
