package dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.board.Inquiry;
import dto.board.Qna;
import utils.DBConn;
import utils.Paging;

public class QnaDaoImpl implements QnaDao {

	// DB 연결 객체 
	private Connection conn = DBConn.getConnection();
	
	// DB 객체 
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public List<Qna> selectPagingList(Paging paging) {
		// 페이징 리스트 조회 쿼리
		String sql="";
		sql += "SELECT * FROM( ";
		sql += "SELECT rownum rnum, Q.* FROM ( ";
		sql += "SELECT  qna_idx, (SELECT nickname FROM userinfo U WHERE U.user_idx = qna.user_idx) nick,hit, ";
		sql += " (SELECT qfile_idx FROM qna_file F WHERE F.qna_idx = qna.qna_idx) file_idx, ";
		sql	+= "title, content,create_date FROM qna  ORDER BY hit DESC ) Q ";
		
			
		if(paging.getSearch()!=null && !"".equals(paging.getSearch())) {
			sql += "WHERE title LIKE '%"+paging.getSearch()+"%'";
		}
		sql += " ORDER BY rnum";
			sql += ")";
			sql += "WHERE rnum between ? AND ?";
				
			// 조회 결과 담을 list 생성 
			List<Qna> list = new ArrayList<>();
				
			try {
				// DB 작업 실행
				ps = conn.prepareStatement(sql);
					
				ps.setInt(1, paging.getStartNo());
				ps.setInt(2, paging.getEndNo());
					
				rs = ps.executeQuery();
					
				// 조회 결과 List에 담기
				while( rs.next()) {
					Qna qna = new Qna();
						
					// rs의 결과 DTO에 하나씩 저장하기
					qna.setRnum(rs.getInt("rnum"));
					qna.setQna_idx(rs.getInt("qna_idx"));;
					qna.setWriter(rs.getString("nick"));
					qna.setTitle(rs.getString("title"));
					qna.setHit(rs.getInt("hit"));
					qna.setqFile_idx(rs.getInt("file_idx"));
					qna.setCreate_date(rs.getDate("create_date"));
						
					// 조회 결과 List에 넣기
					list.add(qna);
						
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
		String sql ="SELECT COUNT(*) FROM qna";
		
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
	public Qna selectQnaByQnaIdx(Qna qna) {
		// 게시글 하나 조회 쿼리
		String sql ="SELECT qna_idx,(SELECT nickname FROM userinfo U WHERE U.user_idx = Q.user_idx) nick , ";
			   sql +=" (SELECT id FROM userinfo U WHERE U.user_idx = Q.user_idx) writer";
			   sql += ",title, content, hit,create_date  FROM qna Q WHERE qna_idx= ?";
		
		// DB 객체 
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		// 결과 담을 객체 생성 
		Qna q = new Qna();
		try {
			
			// DB 작업 수행 
			ps= conn.prepareStatement(sql);
			ps.setInt(1, qna.getQna_idx());
			
			rs = ps.executeQuery();
			while(rs.next()) {
				q.setQna_idx(rs.getInt("qna_idx"));;
				q.setWriter(rs.getString("writer"));
				q.setWriter(rs.getString("nick"));
				q.setTitle(rs.getString("title"));
				q.setContent(rs.getString("content"));
				q.setHit(rs.getInt("hit"));
				q.setCreate_date(rs.getDate("create_date"));
				
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
		return q;
	}


	@Override
	public void updateHit(Qna qna) {
		// 해당 게시글 조회수 증가 쿼리
		String sql ="";
		sql += "UPDATE qna SET hit= hit+1 WHERE qna_idx=?";
		
		try {
					
			// autocommit 끄기
			conn.setAutoCommit(false);
					
			// DB 작업 
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qna.getQna_idx());
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
	public void insert(Qna qna) {
		// 게시글 추가하는 쿼리 
		
		System.out.println("dao에서 qna : "+  qna);
		String sql ="";
		sql+="INSERT INTO qna (qna_idx, title, user_idx,content,hit)"; 
		sql+=	"VALUES(?,?,?,?,0)";
						
		try {
			conn.setAutoCommit(false);
			System.out.println("title :" +qna.getTitle());
			
			ps = conn.prepareStatement(sql);
			
			//DB 작업 
			ps.setInt(1, qna.getQna_idx());
			ps.setString(2, qna.getTitle());
			ps.setInt(3, qna.getUser_idx());
			ps.setString(4, qna.getContent());
					
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
	public void delete(Qna qna) {
		// 게시물 삭제 쿼리 
		String sql = "";
		sql += "DELETE qna WHERE qna_idx= ?";

		try {
			conn.setAutoCommit(false);
			
			//DB작업
			ps= conn.prepareStatement(sql);
			ps.setInt(1, qna.getQna_idx());
			
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
	public void update(Qna qna) {
		// 게시물 수정 쿼리 
		String sql= "";
		sql += "UPDATE qna SET title= ?, content=? ";
		sql += "WHERE qna_idx = ?";

			
		try {
			conn.setAutoCommit(false);
					
			//DB 작업 
			ps = conn.prepareStatement(sql);
			ps.setString(1, qna.getTitle());
			ps.setString(2, qna.getContent());
			ps.setInt(3, qna.getQna_idx());
					
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
	public String selectIdByQnaIdx(Qna qna) {
		// 게시글 번호로 user id 조회하기
		String sql="";
		sql += "SELECT id FROM userinfo U, qna Q" ;
		sql +=	" WHERE Q.user_idx = U.user_idx" ;
		sql +=	" AND Q.qna_idx= ?";

				
		// 결과 저장할 변수
		String id = null;
				
		try {
			// DB 작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qna.getQna_idx());
				
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
	@Override
	public String selectNickByQna(Qna qna) {
		
		// 게시글 번호로 user nickname 조회
		String sql = "";
			   sql +="SELECT nickname FROM userinfo U, qna Q  ";
			   sql +="WHERE Q.user_idx =U.user_idx  ";
			   sql += "AND Q.qna_idx=?";
		
	    // nickname 저장할 변수
		String nick = null;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, qna.getQna_idx());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				nick = rs.getString(1);
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
				
		return nick;
	}
	@Override
	public int selectQnaIdx() {
		// 다음 게시글 번호 조회 쿼리 
		String sql ="SELECT qna_seq.nextval FROM dual";
						
		// DB 객체 
		PreparedStatement ps = null;
		ResultSet rs = null;
						
		// 결과 저장할 변수 
						
		int qna_idx = 0;
						
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
							
			while(rs.next()) {
				qna_idx = rs.getInt(1);
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
			return qna_idx;
	}
	@Override
	public void deleteQnaList(String names) {
		String sql = "DELETE FROM qna WHERE qna_idx IN("+names+")";
		
		//DB 객체
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(sql);
					
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
				if(ps!=null)	ps.close();
						
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
	}
}
