package service.stroy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

import dao.story.StoryDao;
import dao.story.StoryDaoImpl;
import dto.plan.Plan;
import dto.story.Comment;
import dto.story.Story;
import dto.timetable.Timetable;
import service.account.AccountService;
import service.account.AccountServiceImpl;
import utils.CalcDate;

public class StoryServiceImpl implements StoryService {
	
	StoryDao storyDao = new StoryDaoImpl();
	AccountService accountService = new AccountServiceImpl(); 

	@Override
	public List<Story> getStoryList(Plan plan) {
		List<Story> StoryList = new ArrayList<>();
		
		StoryList = storyDao.selectAllByPlanNo(plan);
		
		CalcDate calcDate = new CalcDate();
		
		//몇일차인지 계산
		for(int i = 0 ; i <StoryList.size();i++) {
				Date date;
				try {
					date = new SimpleDateFormat("yyyy-MM-dd").parse(StoryList.get(i).getTravel_day());
					int diffDays = calcDate.CalcPriod(plan.getStart_date(),date);
					StoryList.get(i).setCalcDay(diffDays);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
		}
		
		
		return StoryList;
	}

	@Override
	public Story getParam(HttpServletRequest req) {
		
		Story story = new Story();
		Gson gson = new Gson();
		
		String storyJSON = req.getParameter("JSON");
		
		// plan_idx Set
	    if(storyJSON!=null & !"".equals(storyJSON)){
	      story = gson.fromJson(storyJSON, Story.class);
	    } else { // 테스트용 코드, 테스트 후에는 삭제
	      System.out.println("story가 null 혹은 빈값"); 
	    }
	    
		// user_idx Set
		story.setUser_idx(1);
		
		return story;
	}

	@Override
	public Story getStory(Story story) {
		return storyDao.selectStoryByTtbIdx(story);
	}

	@Override
	public void delete(Story story) {
		// TODO Auto-generated method stub
		storyDao.delete(story);
	}

	@Override
	public void deleteList(Plan plan) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteList(Plan plan, List<Timetable> ttbList) {
		storyDao.delete(plan, ttbList);
	}
	
	@Override
	public void update(Story story) {
		
		storyDao.update(story);
		
	}

	@Override
	public void write(Story story) {
		story.setStory_idx(storyDao.SelectStoryIdx());
		
		storyDao.insert(story);
		
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
