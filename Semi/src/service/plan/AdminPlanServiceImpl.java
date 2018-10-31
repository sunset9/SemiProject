package service.plan;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.account.AccountDao;
import dao.account.AccountDaoImpl;
import dao.contents.ContentsDao;
import dao.contents.ContentsDaoImpl;
import dto.Account.Account;
import dto.plan.Plan;
import dto.user.User;
import utils.Paging;

public class AdminPlanServiceImpl implements AdminPlanService{
	AccountDao accDao = new AccountDaoImpl();
	ContentsDao contentsDao = new ContentsDaoImpl();
	
	@Override
	public Plan getParam(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void delete(Plan plan) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Plan> selectPlanAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Plan> selectPlanByTitle(Plan plan) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//---------------------------------------------------
	
	
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
	public int getCurPage(HttpServletRequest req) {
		// 요청 파라미터 받아오기
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
	public int getTotalCount(int searchType, String search ) {
		return contentsDao.selectCntAll( searchType,search);
	}
	
	@Override
	public List<Plan> getPagingList(Paging paging) {
		return contentsDao.selectPagingList(paging);
	}
	
	

}
