package service.stroy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.story.StoryDao;
import dao.story.StoryDaoImpl;
import dto.plan.Plan;
import dto.story.Comment;
import dto.story.Story;
import service.account.AccountService;
import service.account.AccountServiceImpl;
import utils.Paging;

public class AdminStoryServiceImpl implements AdminStoryService {
	
	StoryDao storyDao = new StoryDaoImpl();
	AccountService accountService = new AccountServiceImpl();

	@Override
	public int getCurPage(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void deleteComment(Comment cmt) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Comment> getPagingList(Paging paging) {
		// TODO Auto-generated method stub
		return null;
	} 

	

}
