package service.bookmark;

import javax.servlet.http.HttpServletRequest;

import dao.bookmark.BookmarkDao;
import dao.bookmark.BookmarkDaoImpl;
import dto.user.Bookmark;
import dto.plan.Plan;

public class BookmarkServiceImpl implements BookmarkService{
	
	BookmarkDao bDao = new BookmarkDaoImpl();
	
	@Override
	public void insertBookmark(Plan plan, int user_idx) {
		bDao.insertBookmarkByPlanIdx(plan, user_idx);
	}
	
	@Override
	public void deleteBookmark(Bookmark book) {
		bDao.deleteBookmark(book);
	}
	
	@Override
	public Bookmark getBookmarkInfo(Plan plan) {
		return bDao.selectBookmarkByPlanIdx(plan);
	}

	@Override
	public Bookmark getParam(HttpServletRequest req) {
		Bookmark book  = new Bookmark();
		book.setBook_idx(Integer.parseInt(req.getParameter("book_idx")) );
		return book;
	}
}
