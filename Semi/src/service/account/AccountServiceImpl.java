package service.account;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.account.AccountDao;
import dao.account.AccountDaoImpl;
import dto.Account.Account;
import dto.story.Story;

public class AccountServiceImpl implements AccountService {
	
	AccountDao accountDao = new AccountDaoImpl();

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
	public List<Account> getPlanAccountList(Story story) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAccountListByStoryIdx(Story story) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Write(Account account) {
		// TODO Auto-generated method stub
		
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
