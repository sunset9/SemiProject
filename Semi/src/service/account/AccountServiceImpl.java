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
		
		return AccountList;
	}

	@Override
	public void deleteAccountListByStoryIdx(Story story) {
		// TODO Auto-generated method stub
		
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

}
