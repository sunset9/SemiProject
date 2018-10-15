package dao.account;

import dto.story.Story;

import java.util.List;

import dto.Account.Account;
import dto.plan.Plan;

public interface AccountDao {

	//plan당 모든 가계부 불러오기
	public List<Account> selectAccountByPlanidx(Plan plan);
	
	//스토리당 모든 가계부 불러오기
	public List<Account> SelectAccountByStoryidx(Story story);
	
	//스토리 하나 삭제할때 딸려있는 가계부 모두 삭제
	public void deleteAccountListByStoryidx(Story story);

	//해당 스토리의 Account 타입이 존재하는지 확인(update할때)
	public int selectCntByAccType(Account account);
	
	// 해당 AccType 삭제
	public void deleteByAccType(Account account);
	
	// 업데이트
	public void update(Account account);
	
	public void insert(Account account);
	
}
