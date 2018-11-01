package service.account;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Account.Account;
import dto.plan.Plan;
import dto.story.Story;

public interface AccountService {
	
		public Account getParam(HttpServletRequest req);
	
		// 스토리당 가계부 모두 불러오기
		public List<Account> getStoryAccountList(Story story);
		// 플랜당 가계부 모두 불러오기
		public List<Account> getPlanAccountList(Plan plan);
	
		//스토리 하나 삭제할때 딸려있는 가계부 모두 삭제
		public void deleteAccountListByStoryIdx(Story story);
		
		//저장하기
		public void Write(Account account);
		
		public void Update(Account account);
		
		public void delete(Account account);
		
		public double calcCost(int currSymbol, double Orgin_cost, double USD_rate, double KRW_rate, double JPY_rate);
		
		//미니뷰 account 저장
		public void writeMini(Story story, Boolean isStory,HttpServletRequest req);
		
}
