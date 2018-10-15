package service.account;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Account.Account;
import dto.story.Story;

public interface AccountService {
	
		public Account getParam(HttpServletRequest req);
	
	// 스토리당 가계부 모두 불러오기
		public List<Account> getStoryAccountList(Story story);
		// 플랜당 가계부 모두 불러오기
		public List<Account> getPlanAccountList(Story story);
	
		//스토리 하나 삭제할때 딸려있는 가계부 모두 삭제
		public void deleteAccountListByStoryIdx(Story story);
		
		public void Write(Account account);
		
		public void Update(Account account);
		
		public void delete(Account account);
		
		
		
}
