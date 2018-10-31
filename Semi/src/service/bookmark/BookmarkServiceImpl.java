package service.bookmark;

import dao.bookmark.BookmarkDao;
import dao.bookmark.BookmarkDaoImpl;
import dto.bookmark.Bookmark;
import dto.plan.Plan;

public class BookmarkServiceImpl implements BookmarkService{
	
	BookmarkDao bDao = new BookmarkDaoImpl();
	
	@Override
	public void insertBookmark(Plan plan) {
		bDao.insertBookmarkByPlanIdx(plan);
	}
	
	@Override
	public void deleteBookmark(Plan plan) {
		bDao.deleteBookmarkByPlanIdx(plan);
	}
	
	@Override
	public Bookmark getBookmarkInfo(Plan plan) {
		return bDao.selectBookmarkByPlanIdx(plan);
	}
}
