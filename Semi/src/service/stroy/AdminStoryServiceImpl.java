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
		String curPage = req.getParameter("curPage");
		
		// null이나 공백이 아닐 때 변수값 반환하기
		if( curPage != null && !"".equals(curPage)) {
			return Integer.parseInt(curPage);
		}
			
		// null이나 공백일 때 0 반환
		return 0;
	}
	
	
	@Override
	public String getSearch(HttpServletRequest req) {
		
		String search = req.getParameter("search");
		return search;
	}
	
	@Override
	public int getTotalCount(String search) {
		return storyDao.selectCmtCnt(search );
	}
	@Override
	public boolean deleteComment(Comment cmt) {
		
		return storyDao.deleteComment(cmt);
	}
	@Override
	public List<Comment> getPagingList(Paging paging) {
		return storyDao.selectCmtPagingList(paging);
	}
	@Override
	public boolean loginCheck(HttpServletRequest req) {
		boolean check =false;
		
		if(req.getSession().getAttribute("login")!=null){
			check = (boolean)req.getSession().getAttribute("login");
		}
		
//		System.out.println("check : " +check);
		return check;
	}


	@Override
	public Comment getParam(HttpServletRequest req) {
		Comment comm = new Comment();
		
		String comm_idx =req.getParameter("comm_idx");
		
		// null 아닐때 저장 
		if( comm_idx != null && !"".equals(comm_idx)) {
			comm.setComm_idx(Integer.parseInt(comm_idx));
		}
		return comm;
	}


	@Override
	public void commListDelete(String names) {
		storyDao.deleteListComm(names);
	}

	

}
