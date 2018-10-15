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

public class StoryServiceImpl implements StoryService {
	
	StoryDao storyDao = new StoryDaoImpl();
	AccountService accountService = new AccountServiceImpl(); 

	@Override
	public List<Story> getStoryList(Plan param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Story getParam(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Story getStory(Story story) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Story story) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteList(Plan plan) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Story story) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(Story story) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Comment> getCommentList(List<Story> storyList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment getCommet(Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCommentList(Story story) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateComment(Comment cmt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteComment(Comment cmt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void writeComment(Comment cmt) {
		// TODO Auto-generated method stub
		
	}

}
