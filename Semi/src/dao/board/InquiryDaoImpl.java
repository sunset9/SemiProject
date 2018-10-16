package dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.board.Inquiry;
import utils.DBConn;
import utils.Paging;

public class InquiryDaoImpl implements InquiryDao {

	// DB 연결 객체
	private Connection conn = DBConn.getConnection();
	
	@Override
	public int selectCntAll() {
		
		// 전체 게시글 수 조회
		String sql ="SELECT COUNT(*) FROM inquiry";
		
		//DB객체 생성 
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		// 조회 결과 저장할 변수 생성 =
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			rs.next();
			
			// 조회 결과중 첫 번째 컬럼값 가져오기
			cnt = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null)	ps.close();
				if(rs!=null)	rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}
	@Override
	public List<Inquiry> selectPagingList(Paging paging) {
		
		// 페이징 리스트 조회 쿼리
		String sql="";
		sql += "SELECT * FROM(";
		sql += "SELECT rownum rnum, I.* FROM ( ";
		sql += "SELECT * FROM inquiry ORDER BY inq_idx DESC ) I ";
		sql += "ORDER BY rnum ) WHERE rnum between ? AND ? "; 
		
		//DB 객체 생성 
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		// 조회 결과 담을 list 생성 
		List<Inquiry> list = new ArrayList<>();
		
		try {
			// DB 작업 실행
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			// 조회 결과 List에 담기
			while( rs.next()) {
				Inquiry inq = new Inquiry();
				
				// rs의 결과 DTO에 하나씩 저장하기
				inq.setInq_idx(rs.getInt("inq_idx"));
				inq.setUser_idx(rs.getInt("user_idx"));
				inq.setTitle(rs.getString("title"));
				inq.setAnswer(rs.getInt("answer"));
				inq.setHit(rs.getInt("hit"));
				inq.setCreate_date(rs.getDate("create_date"));
				
				// 조회 결과 List에 넣기
				list.add(inq);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				//DB객체 닫기
				if(rs!=null)	rs.close();
				if(ps!=null)	ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 결과 반환
		return list;
	}


	@Override
	public Inquiry selectInqByInqIdx(Inquiry inq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateHit(Inquiry inq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Inquiry inq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Inquiry inq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Inquiry inq) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Inquiry> selectInqByAnswer() {
		// TODO Auto-generated method stub
		return null;
	}

}
