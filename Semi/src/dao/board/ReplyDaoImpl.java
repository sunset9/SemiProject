package dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.board.Inquiry;
import dto.board.Reply;
import utils.DBConn;

public class ReplyDaoImpl implements ReplyDao {

	// DB 객체 
	private Connection conn = DBConn.getConnection();
	
	// JDBC 객체 
	private PreparedStatement ps;
	private ResultSet rs ;
	
	
	@Override
	public List<Reply> selectInqByInqIdx(Inquiry inquiry) {
		String sql ="SELECT * FROM ( " ;
			   sql +="SELECT rownum rnum, I.* FROM (";
			   sql +="	SELECT rep_idx,inq_idx,(SELECT id FROM userinfo U WHERE U.user_idx= R.user_idx) id, content,create_date FROM inquiry_reply R" ; 
			   sql +="	WHERE inq_idx=? ORDER BY create_date )I )";
			   sql +="	ORDER BY rnum DESC" ;
				
		List<Reply> repList = new ArrayList<>();
		
		try {
			ps= conn.prepareStatement(sql);
			
			ps.setInt(1, inquiry.getInq_idx());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Reply reply = new Reply();
				
				reply.setRep_idx(rs.getInt("rep_idx"));
				reply.setInq_idx(rs.getInt("inq_idx"));
				reply.setUserid(rs.getString("id"));
				reply.setContent(rs.getString("content"));
				reply.setCreate_date(rs.getDate("create_date"));
				
				repList.add(reply);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return repList;
	}


	@Override
	public void delete(Reply reply) {
		System.out.println(reply);
		String sql ="DELETE inquiry_reply ";
				sql += "WHERE rep_idx = ? ";
				
			try {
				conn.setAutoCommit(false);
				
				ps = conn.prepareStatement(sql);
				ps.setInt(1, reply.getRep_idx());
				
				ps.executeUpdate();
				
				conn.commit();
			
			} catch (SQLException e) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
				}finally {
					try {
						if(ps!=null)	ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}


	@Override
	public void insertReply(Reply reply) {
		
		
		String sql = "INSERT INTO inquiry_reply ("  ;
			   sql += " rep_idx,inq_idx,user_idx,content) " ;
			   sql += "VALUES(inquiry_reply_seq.nextval,?,?,?)";
			   
		try {
			conn.setAutoCommit(false);
			
			ps=conn.prepareStatement(sql);
			
			ps.setInt(1, reply.getInq_idx());
			ps.setInt(2, reply.getUser_idx());
			ps.setString(3, reply.getContent());
			
			ps.executeQuery();
			
			conn.commit();
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}


	@Override
	public int countReply(Reply reply) {
		// 게시글 존재 여부 확인 쿼리
		String sql ="SELECT COUNT (*) FROM inquiry_reply WHERE rep_idx = ?";
		
		// 결과 저장할 변수 
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, reply.getRep_idx());
			rs= ps.executeQuery();
			rs.next();
			cnt= rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return cnt;
	}

}
