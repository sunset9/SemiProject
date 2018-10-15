package dao.story;

import java.util.List;

import dao.account.AccountDao;
import dao.account.AccountDaoImpl;
import dto.plan.Plan;
import dto.story.Comment;
import dto.story.Story;
import service.plan.PlanService;
import service.stroy.StoryService;
import service.stroy.StoryServiceImpl;

public class StoryDaoImpl implements StoryDao{
	
	@Override
	public List<Story> selectAllByPlanNo(Plan Plan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Story selectStoryByStoryIdx(Story story) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateHit(Story story) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Story story) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Story story) {
		// TODO Auto-generated method stub
		
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
	public int SelectCntAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Comment> selectCommentByStoryNo(Story story) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment selectCommentByCommentIdx(Comment cmt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCommentList(Story story) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertComment() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteComment() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateComment() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Comment selectCommentByContent(Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}

}
