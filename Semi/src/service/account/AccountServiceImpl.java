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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getPlanAccountList(Plan plan) {
		
		List<Account> AccountList = new ArrayList<>();
		
		AccountList = accountDao.selectAccountByPlanidx(plan);
		
		for(int i =0; i<AccountList.size();i++) {
			AccountList.get(i).setCategory_name(cateGoryIntToStr(AccountList.get(i).getCategory()));
			AccountList.get(i).setCurr_idx_name(currIntToStr(AccountList.get(i).getCurr_idx()));
			Story story = new Story();
			story.setStory_idx(AccountList.get(i).getStory_idx());
			story = storyDao.selectStoryByStoryIdx(story);
			
			AccountList.get(i).setTtb_idx(story.getTtb_idx());
		}
		
		
		return AccountList;
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
	public Float calcCost(int currSymbol, float Orgin_cost, float USD_rate, float KRW_rate, float JPY_rate) {
		
		float result = 0;
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


}
