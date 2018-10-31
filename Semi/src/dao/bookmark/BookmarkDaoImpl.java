package dao.bookmark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.bookmark.Bookmark;
import dto.plan.Plan;
import utils.DBConn;

public class BookmarkDaoImpl implements BookmarkDao{
	
	private Connection conn = DBConn.getConnection();
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public void insertBookmarkByPlanIdx(Plan plan) {
		String sql = "INSERT INTO bookmark("
				+ "book_idx, user_idx, plan_idx"
				+ ")"
				+ " VALUES("
				+ "bookmark_seq.nextval, ?, ?"
				+ " )";

		try {
			// 오토커밋 해제
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, plan.getUser_idx());
			ps.setInt(2, plan.getPlan_idx());
			
			
			ps.executeUpdate();
			conn.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteBookmarkByPlanIdx(Plan plan) {
		String sql = "delete bookmark"
				+ " where plan_idx = ?";
		try {
			// 오토커밋 해제
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, plan.getPlan_idx());
			
			ps.executeUpdate();
			
			conn.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if(ps!=null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Bookmark selectBookmarkByPlanIdx(Plan plan) {
		String sql = "";
		sql += "SELECT * FROM bookmark";
		sql += " WHERE plan_idx = ?";
		
		//조회 결과 담을 DTO
		Bookmark bookInfo = new Bookmark();
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, plan.getPlan_idx());
			rs = ps.executeQuery();
			
			//결과 담기
			while(rs.next()) {
				//결과 행 DTO에 저장
				bookInfo.setBook_idx( rs.getInt("book_idx") );
				bookInfo.setUser_idx( rs.getInt("user_idx") );
				bookInfo.setPlan_idx( rs.getInt("plan_idx") );
				
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
		// 전체조회 결과 반환
		return bookInfo;
	}
}
