package dao.account;

import java.util.List;

import dto.Account.Account;
import dto.plan.Plan;
import dto.story.Story;

public class AccountDaoImpl implements AccountDao {

	@Override
	public List<Account> selectAccountByPlanidx(Plan plan) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> SelectAccountByStoryidx(Story story) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAccountListByStoryidx(Story story) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int selectCntByAccType(Account account) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteByAccType(Account account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Account account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Account account) {
		// TODO Auto-generated method stub
		
	}

}
