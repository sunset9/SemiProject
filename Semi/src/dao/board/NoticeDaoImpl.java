package dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.board.Notice;
import utils.DBConn;
import utils.Paging;

public class NoticeDaoImpl implements NoticeDao {

	//DB 연결 객체 
	private Connection conn = DBConn.getConnection();
	
	//DB 객체 생성 
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	
	@Override
	public List<Notice> selectPagingList(Paging paging) {
		// 페이징 리스트 조회 쿼리
		String sql="";
		sql += "SELECT * FROM(";
		sql += "SELECT rownum rnum, N.* FROM ( ";
		sql += "SELECT notice_idx, (SELECT nickname FROM userinfo U WHERE U.user_idx = NO.user_idx) nick,hit, ";
		sql += "title, content,create_date";
		sql += " FROM notice NO ORDER BY notice_idx DESC ) N ";
		

		if(paging.getSearch()!=null && !"".equals(paging.getSearch())) {
			sql += "WHERE title LIKE '%"+paging.getSearch()+"%'";
		}

		sql += " ORDER BY rnum";
		sql += ")";
		sql += "WHERE rnum between ? AND ?";
		
		// 조회 결과 담을 list 생성 
		List<Notice> list = new ArrayList<>();
		
		try {
			// DB 작업 실행 
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());
			
			rs = ps.executeQuery();
			
			// 조회 결과 List에 담기 
			while(rs.next()) {
				Notice noti = new Notice();
				
				noti.setNotice_idx(rs.getInt("notice_idx"));
				noti.setWriter(rs.getString("nick"));
				noti.setTitle(rs.getString("title"));
				noti.setContent(rs.getString("content"));
				noti.setHit(rs.getInt("hit"));
				noti.setCreate_date(rs.getDate("create_date"));
				
				list.add(noti);
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
	public int selectCntAll(String search) {
		// 전체 게시글 수 조회
		String sql ="SELECT COUNT(*) FROM notice";
		
		if(search !=null && !"".equals(search)) {
			sql+=" WHERE title LIKE '%"+search+"%'";
		}
		
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
	public void updateHit(Notice notice) {
		// 해당 게시글 조회수 증가 쿼리
			String sql ="";
			sql += "UPDATE notice SET hit= hit+1 WHERE notice_idx=?";
			
			//DB 객체
			PreparedStatement ps = null;
			
			try {
				
				// autocommit 끄기
				conn.setAutoCommit(false);
				
				// DB 작업 
				ps = conn.prepareStatement(sql);
				ps.setInt(1, notice.getNotice_idx());
				ps.executeQuery();
				
				// 정상 종료일 경우 commit 하기
				conn.commit();
			} catch (SQLException e) {
				try {
					// 예외 발생시 rollback하기
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}finally {
					try {
						//DB객체 닫기
						if(ps!=null)	ps.close();
						
					} catch (SQLException e) {
						e.printStackTrace();
				}
			}
			
	}

	@Override
	public void insert(Notice notice) {
		// 게시글 추가하는 쿼리 
		String sql ="";
		sql+="INSERT INTO notice (notice_idx, title, user_idx,content,hit)"; 
		sql+=	"VALUES(notice_seq.nextval,?,?,?,0)";
				
		//DB 객체 
		PreparedStatement ps = null;
				
		try {
			conn.setAutoCommit(false);
			
			//DB 작업 
			ps = conn.prepareStatement(sql);
			ps.setInt(1, notice.getNotice_idx());
			ps.setString(2, notice.getTitle());
			ps.setInt(3, notice.getUser_idx());
			ps.setString(4, notice.getContent());
			
			ps.executeUpdate();
					
			conn.commit();
		} catch (SQLException e) {
			try {
				// 비정상 종료시 rollback
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				//DB객체 닫기
				if(ps!=null)	ps.close();
						
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public void delete(Notice notice) {
		// 게시물 삭제 쿼리 
		String sql = "";
		sql += "DELETE notice WHERE notice_idx= ?";
		
		// DB 객체 
		PreparedStatement ps = null;
		
		try {
			conn.setAutoCommit(false);
			
			//DB작업
			ps= conn.prepareStatement(sql);
			ps.setInt(1, notice.getNotice_idx());
			
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
				//DB객체 닫기
				if(ps!=null)	ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public void update(Notice notice) {
		// 게시물 수정 쿼리 
		String sql= "";
		sql += "UPDATE notice SET title= ?, content=? ";
		sql += "WHERE notice_idx = ?";

		
		try {
			conn.setAutoCommit(false);
			
			//DB 작업 
			ps = conn.prepareStatement(sql);
			ps.setString(1, notice.getTitle());
			ps.setString(2, notice.getContent());
			ps.setInt(3, notice.getNotice_idx());
			
			ps.executeUpdate();
			
			conn.commit();
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				//DB객체 닫기
				if(ps!=null)	ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
		

	@Override
	public Notice selectNoticeByNoticeIdx(Notice notice) {
			// 게시글 하나 조회 쿼리
			String sql ="SELECT * FROM notice WHERE notice_idx=?";
				
			
			// 결과 담을 객체 생성 
			Notice noti = new Notice();
			try {
				
				// DB 작업 수행 
				ps= conn.prepareStatement(sql);
				ps.setInt(1, notice.getNotice_idx());
				
				rs = ps.executeQuery();
				
				while(rs.next()) {
					noti.setNotice_idx(rs.getInt("notice_idx"));
					noti.setUser_idx(rs.getInt("user_idx"));
					noti.setTitle(rs.getString("title"));
					noti.setContent(rs.getString("content"));
					noti.setHit(rs.getInt("hit"));
					noti.setCreate_date(rs.getDate("create_date"));
					
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
			return noti;
		}

	@Override
	public String selectIdByNoticeIdx(Notice notice) {
		// 게시글 번호로 user id 조회하기
		String sql="";
		sql += "SELECT id FROM userinfo U, Notice N" ;
		sql +=	" WHERE N.user_idx = U.user_idx" ;
		sql +=	" AND N.notice_idx= ?";

		
		// 결과 저장할 변수
		String id = null;
		
		try {
			// DB 작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, notice.getNotice_idx());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				id = rs.getString(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				
				//DB객체 닫기
				if(ps!=null)	ps.close();
				if(rs!=null)	rs.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return id;
	}

}
