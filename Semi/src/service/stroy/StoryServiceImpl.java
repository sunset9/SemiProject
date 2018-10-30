package service.stroy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

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
import service.account.AccountService;
import service.account.AccountServiceImpl;
import service.timetable.TimetableService;
import service.timetable.TimetableServiceImpl;
import utils.CalcDate;

public class StoryServiceImpl implements StoryService {
	
	StoryDao storyDao = new StoryDaoImpl();
	UserDao userDao = new UserDaoImpl();
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
					Story story = new Story();
					date = new SimpleDateFormat("yyyy-MM-dd").parse(StoryList.get(i).getTravel_day());
					int diffDays = calcDate.CalcPriod(plan.getStart_date(),date);
					StoryList.get(i).setCalcDay(diffDays);
					story.setStory_idx(StoryList.get(i).getStory_idx());
					StoryList.get(i).setCommCnt(storyDao.selectCntComm(story));
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
	public void deleteListByPlanIdx(Plan plan) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteCommentListByStoryIdx(Story story) {
		// TODO Auto-generated method stub
		
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
	public void writeMini(int ttb_idx, Story storyParam, boolean isStory) {
		Story story = storyParam;
		story.setTtb_idx(ttb_idx);
		
		System.out.println("----- 2. insert할 객체 -----");
		System.out.println(story);
		
		if(isStory) {
			System.out.println(">> 이미 작성된 스토리 있음");
			storyDao.update(story);
		} else { // 첫 스토리 작성인 경우
			System.out.println(">> 첫 스토리 작성");
			storyDao.insert(story);
		}

		// Timetable Service 객체 생성
//		TimetableService ttbService = new TimetableServiceImpl();
		
		// 요청 파라미터 추출
//		Story storyParam = getParam(req);
//		Map<Timetable, Location> ttbLocParam = ttbService.getMiniParam(req);
		
//		System.out.println("----- 1.스토리 서비스 param -----");
//		System.out.println(storyParam);
//		System.out.println(ttbLocParam);
		
		// ttb_idx가 음수라면(아직 DB저장안된 타임테이블) 새로운 idx 값 가져옴
//		if(storyParam.getTtb_idx() < 0) {
//			int ttb_idx = ttbService.getTtbIdx();
//			
//			// 새로운 타임테이블이기 때문에 타임테이블 저장
//			ttbService.writeTtb(ttb_idx, ttbLocParam);
//			
//			// 미니뷰 스토리 저장 (ttb_idx 세팅한 후 저장)
//			Story story = storyParam;
//			story.setTtb_idx(ttb_idx);
//			System.out.println("----- 2. insert할 객체 -----");
//			System.out.println(story);
//			storyDao.insert(story);
//			
//		} else { // 기존에 있던 타임테이블의 미니뷰 스토리를 수정한 경우
//			// 스토리가 이미 있었던 타임테이블이라면
//			if(ttbService.isStory(ttbLocParam.keySet().iterator().next())) {
//				storyDao.update(storyParam);
//			} else { // 첫 스토리 작성인 경우
//				storyDao.insert(storyParam);
//			}
//		}
		
	}

}
