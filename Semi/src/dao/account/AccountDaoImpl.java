package dao.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Account.Account;
import dto.plan.Plan;
import dto.story.Story;
import utils.DBConn;

public class AccountDaoImpl implements AccountDao {
	private Connection conn = DBConn.getConnection();
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	@Override
	public List<Account> selectAccountByPlanidx(Plan plan) {
		
		String sql = "select * from Account where plan_idx = ?";
		
		List<Account> AccountList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, plan.getPlan_idx());
			
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				Account account = new Account();
				
				account.setAcc_idx(rs.getInt("acc_idx"));
				account.setPlan_idx(rs.getInt("plan_idx"));
				account.setStory_idx(rs.getInt("story_idx"));
				account.setCurr_idx(rs.getInt("curr_idx"));
				account.setCategory(rs.getInt("acc_cat_idx"));
				account.setOrigin_cost(rs.getFloat("origin_cost"));
				account.setCaled_cost(rs.getFloat("caled_cost"));
				
				AccountList.add(account);
			}
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) rs.close();
				if (ps != null) ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
		
		return AccountList;
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
		
		String sql = "INSERT INTO account(acc_idx,plan_idx,story_idx,curr_idx,acc_cat_idx,origin_cost,caled_cost) VALUES(?,?,?,?,?,?,?)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, account.getAcc_idx());
			ps.setInt(2, account.getPlan_idx());
			ps.setInt(3, account.getStory_idx());
			ps.setInt(4, account.getCurr_idx());
			ps.setInt(5, account.getCategory());
			ps.setFloat(6, account.getOrigin_cost());
			ps.setFloat(7, account.getCaled_cost());
			
			ps.executeUpdate();
			
			conn.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				try {
					if (rs != null) rs.close();
					if (ps != null) ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
	}

	@Override
	public int selectAccIdx() {
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "SELECT account_seq.nextval";
		sql += " FROM dual";
		
		//DB 객체
		//게시글번호
		int acc_idx = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			//결과 담기
			while(rs.next()) {
				acc_idx = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				//DB객체 닫기
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//게시글 번호 반환
		return acc_idx;
	}

}
