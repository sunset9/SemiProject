package service.stroy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

import dao.account.AccountDao;
import dao.account.AccountDaoImpl;
import dao.story.StoryDao;
import dao.story.StoryDaoImpl;
import dao.user.UserDao;
import dao.user.UserDaoImpl;
import dto.plan.Plan;
import dto.story.Comment;
import dto.story.Story;
import dto.timetable.Location;
import dto.timetable.Timetable;
import dto.user.User;
import service.timetable.TimetableService;
import service.timetable.TimetableServiceImpl;
import utils.CalcDate;

public class StoryServiceImpl implements StoryService {
	
	StoryDao storyDao = new StoryDaoImpl();
	UserDao userDao = new UserDaoImpl();
	AccountDao accountDao = new AccountDaoImpl();

	@Override
	public List<Story> getStoryList(Plan plan) {
		List<Story> StoryList = new ArrayList<>();
		
		StoryList = storyDao.selectAllByPlanNo(plan);
		
		CalcDate calcDate = new CalcDate();
		
		//몇일차인지 계산
		for(int i = 0 ; i <StoryList.size();i++) {
				Date date;
				try {
					Story story = new Story();
					
					// 일차계산
					date = new SimpleDateFormat("yyyy-MM-dd").parse(StoryList.get(i).getTravel_day());
					int diffDays = calcDate.CalcPriod(plan.getStart_date(),date);
					StoryList.get(i).setCalcDay(diffDays);
					
					//커멘드 갯수
					story.setStory_idx(StoryList.get(i).getStory_idx());
					StoryList.get(i).setCommCnt(storyDao.selectCntComm(story));
					
					//가계부 갯수
					StoryList.get(i).setAccCnt(accountDao.selectCntByStoryIdx(story));
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
		
	    if(storyJSON!=null & !"".equals(storyJSON)){
	      story = gson.fromJson(storyJSON, Story.class);
	    } else { 
	      System.out.println("story가 null 혹은 빈값"); 
	    }
	    
	   int userIdx =  (int) req.getSession().getAttribute("user_idx");
	   
	   story.setUser_idx(userIdx);
	   
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
	public void deleteList(Plan plan, List<Timetable> ttbList) {
		storyDao.delete(plan, ttbList);
	}
	
	@Override
	public void update(Story story) {
		
		storyDao.update(story);
		
	}

	@Override
	public int write(Story story) {
		
		story.setStory_idx(storyDao.SelectStoryIdx());
		
		storyDao.insert(story);
		
		return story.getStory_idx();
		
	}

	@Override
	public List<Comment> getCommentList(Plan plan) {
		
		List<Comment> list = storyDao.selectCommentByPlanIdx(plan);
		
		for (int i = 0; i<list.size();i++) {
			
			User user = new User();
			user.setUser_idx(list.get(i).getUser_idx());
			
			user = userDao.selectUserByUserIdx(user);
			
			list.get(i).setProfile(user.getProfile());
			list.get(i).setNickname(user.getNickname());
			
		}
		
		return list;
	}

	@Override
	public Comment getCommet(Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateComment(Comment cmt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteComment(Comment cmt) {
		
		storyDao.deleteComment(cmt);
		
	}

	@Override
	public void writeComment(Comment cmt) {
		
		cmt.setComm_idx(storyDao.selectCommentIdx());
		
		storyDao.insertComment(cmt);
		
	}

	@Override
	public void deleteStory(Plan plan) {
		// TODO Auto-generated method stub
		storyDao.deleteStoryListByPlanIdx(plan);
	}


	@Override
	public void deleteCommentListByStoryIdx(Story story) {
		
		storyDao.deleteCommentListByStoryIdx(story);
		
	}

	@Override
	public void deleteCommentListByPlanIdx(Plan plan) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCommentListByTtbIdx(Timetable tb) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Story writeMini(int ttb_idx, Story storyParam, boolean isStory) {
		Story story = storyParam;
		
		story.setTtb_idx(ttb_idx);
		
		if(isStory) { // 이미 작성된 스토리가 있는 경우
			Story st = new Story();
			st = storyDao.selectStoryByTtbIdx(story);
			story.setStory_idx(st.getStory_idx());
			storyDao.update(story);
		} else { // 첫 스토리 작성인 경우
			story.setStory_idx(storyDao.SelectStoryIdx());
			storyDao.insert(story);
		}
		

		return story;
	}

}
