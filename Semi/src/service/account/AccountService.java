package service.account;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Account.Account;
import dto.plan.Plan;
import dto.story.Story;
import dto.timetable.Timetable;

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
		
		//업데이트하기
		public void Update(Account account);
		
		//삭제하기
		public void delete(Account account);
		
		//비용 환율 적용해서 계산하기
		public double calcCost(int currSymbol, double Orgin_cost, double USD_rate, double KRW_rate, double JPY_rate);
		
		//미니뷰 account 저장
		public void writeMini(Story story, Boolean isStory,HttpServletRequest req);
		
		// 삭제한 타임테이블 리스트에 해당하는 가계부 리스트 삭제
		public void deleteList(Plan planParam, List<Timetable> ttbList);
		
}
