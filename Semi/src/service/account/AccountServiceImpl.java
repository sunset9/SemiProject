package service.account;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.account.AccountDao;
import dao.account.AccountDaoImpl;
import dao.story.StoryDao;
import dao.story.StoryDaoImpl;
import dto.Account.Account;
import dto.plan.Plan;
import dto.story.Story;

public class AccountServiceImpl implements AccountService {
	
	AccountDao accountDao = new AccountDaoImpl();
	StoryDao storyDao = new StoryDaoImpl();

	@Override
	public Account getParam(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getStoryAccountList(Story story) {
		
		List<Account> accountList = new ArrayList<>();
		
		accountList = accountDao.SelectAccountByStoryidx(story);
		
		for(int i =0; i<accountList.size();i++) {
			//카테고리 이름, 통화명  dto에 string으로 저장 
			accountList.get(i).setCategory_name(cateGoryIntToStr(accountList.get(i).getCategory()));
			accountList.get(i).setCurr_idx_name(currIntToStr(accountList.get(i).getCurr_idx()));
			Story st = new Story();
			st.setStory_idx(accountList.get(i).getStory_idx());
			st = storyDao.selectStoryByStoryIdx(story);
			
			accountList.get(i).setTtb_idx(story.getTtb_idx());
		}
		
		
		return accountList;
	}

	@Override
	public List<Account> getPlanAccountList(Plan plan) {
		
		List<Account> accountList = new ArrayList<>();
		
		accountList = accountDao.selectAccountByPlanidx(plan);
		
		for(int i =0; i<accountList.size();i++) {
			accountList.get(i).setCategory_name(cateGoryIntToStr(accountList.get(i).getCategory()));
			accountList.get(i).setCurr_idx_name(currIntToStr(accountList.get(i).getCurr_idx()));
			Story story = new Story();
			story.setStory_idx(accountList.get(i).getStory_idx());
			story = storyDao.selectStoryByStoryIdx(story);
			
			accountList.get(i).setTtb_idx(story.getTtb_idx());
		}
		
		
		return accountList;
	}
	
	public String cateGoryIntToStr(int category) {
		
		String category_name = "";
		
	  switch (category) {
	  	case 1:
	  		category_name ="항공료";
		    break;
	  	case 2:
	  	    category_name ="교통";
	  	    break;
	  	case 3:
	  		category_name ="숙박";
	  		break;
	  	case 4:
	  		category_name ="입장료";
	  		break;
	  	case 5:
	  		category_name ="음식";
	  		break;
	  	case 6:
	  		category_name ="오락";
	  		break;
	  	case 7:
	  		category_name ="쇼핑";
	  		break;
	  	case 8:
	  		category_name ="기타";
	  		break;
	  }
		
		return category_name;
	}
	public String currIntToStr(int curr_idx) {
		
		String curr_name = "";
		
		switch (curr_idx) {
		case 1:
			curr_name ="USD";
			break;
		case 2:
			curr_name ="KRW";
			break;
		case 3:
			curr_name ="JPY";
			break;
		
		}
		
		return curr_name;
	}

	@Override
	public void deleteAccountListByStoryIdx(Story story) {
	
		accountDao.deleteAccountListByStoryidx(story);
		
	}

	@Override
	public void Write(Account account) {
		
		account.setAcc_idx(accountDao.selectAccIdx());
		accountDao.insert(account);
		
	}

	@Override
	public void Update(Account account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Account account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double calcCost(int currSymbol, double Orgin_cost, double USD_rate, double KRW_rate, double JPY_rate) {
		
		double result = 0;
		switch (currSymbol) {
		case 1:
			//USD일때
			result = Orgin_cost*USD_rate;
			
			break;
		case 2:
			//KRW일때
			result = Orgin_cost*KRW_rate;
			break;
		case 3:
			//JPY일떄
			result = Orgin_cost*JPY_rate;
			break;
		}
		
		return result;
	}

	@Override
	public void writeMini(Story story, Boolean isStory, HttpServletRequest req) {
		
		//환율 불러오기
		double USD_rate = Float.parseFloat(req.getParameter("USD_rate"));
		double KRW_rate = Float.parseFloat(req.getParameter("KRW_rate"));
		double JPY_rate = Float.parseFloat(req.getParameter("JPY_rate"));
		
		String[] accType = req.getParameterValues("accType");
		String[] currSymbol = req.getParameterValues("currSymbol");
		String[] cost = req.getParameterValues("cost");
		
//		System.out.println("-----------------TEST----------------------");
//		System.out.println(USD_rate);
//		System.out.println(KRW_rate);
//		System.out.println(JPY_rate);
//		System.out.println("-----------------TEST1111---------------------");
//		for (int i =0 ; i<accType.length;i++) {
//			
//			System.out.println(accType[i]);
//			System.out.println(currSymbol[i]);
//			System.out.println(cost[i]);
//		}
//		
//		System.out.println("-----------------TEST----------------------");
//		
		
		if(isStory) { // 이미 작성된 스토리가 있는 경우
			accountDao.deleteAccountListByStoryidx(story);
			
		} else { // 첫 스토리 작성인 경우
		
		}
		
		for (int i =0 ;i<accType.length;i++) {
			if ((cost[i] != null && cost[i] != "")
				&& (currSymbol[i] != null && currSymbol[i] != null) 
				&&  (accType[i] != null && accType[i] != null)) {
				
				Account account= new Account();
				
				account.setAcc_idx(accountDao.selectAccIdx());
				account.setCategory(Integer.parseInt(accType[i]));
				account.setCurr_idx(Integer.parseInt(currSymbol[i]));
				cost[i]=cost[i].replaceAll(",", "");
				account.setOrigin_cost(Double.parseDouble(cost[i]));
				account.setPlan_idx(story.getPlan_idx());
				account.setStory_idx(story.getStory_idx());
				account.setCaled_cost(
						calcCost(account.getCurr_idx(), account.getOrigin_cost(), USD_rate, KRW_rate, JPY_rate)
						);
				accountDao.insert(account);
			
			}
			
	}
		
	
		
		
	}


}
