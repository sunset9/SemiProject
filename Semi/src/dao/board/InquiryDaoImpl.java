package dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import utils.DBConn;
import dto.board.Inquiry;
import utils.Paging;

public class InquiryDaoImpl implements InquiryDao {

	// DB 연결 객체
	private Connection conn = DBConn.getConnection();
	
	@Override
	public int selectCntAll(String search) {
		
		// 전체 게시글 수 조회
		String sql ="SELECT COUNT(*) FROM inquiry";
		
		if(search !=null && !"".equals(search)) {
			sql+=" WHERE title LIKE '%"+search+"%'";
		}
		
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
		sql += "SELECT * FROM( ";
		sql += "SELECT rownum rnum, I.* FROM ( ";
		sql += "SELECT  inq_idx, (SELECT nickname FROM userinfo U WHERE U.user_idx = INQ.user_idx) nick,hit, ";
		sql += " (SELECT count(ifile_idx) FROM inquiry_file IF WHERE IF.inq_idx = INQ.inq_idx ) file_idx, ";
		sql	+= "title, content,create_date,answer FROM inquiry INQ ORDER BY inq_idx DESC ) I ";
		
		
		if(paging.getSearch()!=null && !"".equals(paging.getSearch())) {
			sql += "WHERE title LIKE '%"+paging.getSearch()+"%'";
		}

		sql += " ORDER BY rnum";
		sql += ")";
		sql += "WHERE rnum between ? AND ?";
		
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
				inq.setRnum(rs.getInt("rnum"));
				inq.setInq_idx(rs.getInt("inq_idx"));
				inq.setWriter(rs.getString("nick"));
				inq.setTitle(rs.getString("title"));
				inq.setAnswer(rs.getInt("answer"));
				inq.setHit(rs.getInt("hit"));
				inq.setiFile_idx(rs.getInt("file_idx"));
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
		// 게시글 하나 조회 쿼리
		String sql ="SELECT * FROM inquiry WHERE inq_idx=?";
		
		// DB 객체 
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		// 결과 담을 객체 생성 
		Inquiry i = new Inquiry();
		try {
			
			// DB 작업 수행 
			ps= conn.prepareStatement(sql);
			ps.setInt(1, inq.getInq_idx());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				i.setInq_idx(rs.getInt("inq_idx"));
				i.setUser_idx(rs.getInt("user_idx"));
				i.setTitle(rs.getString("title"));
				i.setContent(rs.getString("content"));
				i.setHit(rs.getInt("hit"));
				i.setAnswer(rs.getInt("answer"));
				i.setCreate_date(rs.getDate("create_date"));
				
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
		return i;
	}

	@Override
	public void updateHit(Inquiry inq) {

		// 해당 게시글 조회수 증가 쿼리
		String sql ="";
		sql += "UPDATE inquiry SET hit= hit+1 WHERE inq_idx=?";
		
		//DB 객체
		PreparedStatement ps = null;
		
		try {
			
			// autocommit 끄기
			conn.setAutoCommit(false);
			
			// DB 작업 
			ps = conn.prepareStatement(sql);
			ps.setInt(1, inq.getInq_idx());
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
	public void insert(Inquiry inq) {
		
		System.out.println("inqdaoimpl : "+inq);
		// 게시글 추가하는 쿼리 
		String sql ="";
		sql+="INSERT INTO inquiry (inq_idx, title, user_idx,content,hit,answer)"; 
		sql+=	"VALUES(?,?,?,?,0,0)";
		
		//DB 객체 
		PreparedStatement ps = null;
		
		try {
			conn.setAutoCommit(false);
			
			//DB 작업 
			ps = conn.prepareStatement(sql);
			ps.setInt(1, inq.getInq_idx());
			ps.setString(2, inq.getTitle());
			ps.setInt(3, inq.getUser_idx());
			ps.setString(4, inq.getContent());
			
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
	public void delete(Inquiry inq) {
		// 게시물 삭제 쿼리 
		String sql = "";
		sql += "DELETE inquiry WHERE inq_idx= ?";
		
		// DB 객체 
		PreparedStatement ps = null;
		
		String nick =null;
		
		try {
			conn.setAutoCommit(false);
			
			//DB작업
			ps= conn.prepareStatement(sql);
			ps.setInt(1, inq.getInq_idx());
			
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
	public void update(Inquiry inq) {
		// 게시물 수정 쿼리 
		String sql= "";
		sql += "UPDATE inquiry SET title= ?, content=? ";
		sql += "WHERE inq_idx = ?";
		
		// DB 객체 
		PreparedStatement ps = null;
		
		try {
			conn.setAutoCommit(false);
			
			//DB 작업 
			ps = conn.prepareStatement(sql);
			ps.setString(1, inq.getTitle());
			ps.setString(2, inq.getContent());
			ps.setInt(3, inq.getInq_idx());
			
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
	public List<Inquiry> selectInqByAnswer(Paging paging) {

		// 페이징 리스트 조회 쿼리
				String sql="";
				sql += "SELECT * FROM( ";
				sql += "SELECT rownum rnum, I.* FROM ( ";
				sql += "SELECT  inq_idx, (SELECT nickname FROM userinfo U WHERE U.user_idx = INQ.user_idx) nick,hit, ";
				sql	+= "title, content,create_date,answer FROM inquiry INQ ";
				sql	+= " WHERE answer=0 ORDER BY inq_idx DESC ) I ";
				
				
				if(paging.getSearch()!=null && !"".equals(paging.getSearch())) {
					sql += "WHERE title LIKE '%"+paging.getSearch()+"%'";
				}

				sql += " ORDER BY rnum";
				sql += ")";
				sql += "WHERE rnum between ? AND ?";
				
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
						inq.setWriter(rs.getString("nick"));
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
	public String selectIdByInq_idx(Inquiry inq) {
		
		// 게시글 번호로 id 조회하기
		String sql="";
		sql += "SELECT id FROM userinfo U, inquiry I" ;
		sql +=	" WHERE I.user_idx = U.user_idx" ;
		sql +=	" AND I.inq_idx= ?";
		
		//DB 객체 
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		// 결과 저장할 변수
		String email = null;
		
		try {
			// DB 작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, inq.getInq_idx());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				email = rs.getString(1);
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
		
		return email;
	}
	@Override
	public String selectNickByInq_idx(Inquiry inq) {
		// 게시글 번호로 닉네임 조회하기
		String sql="";
		sql += "SELECT nickname FROM userinfo U, inquiry I" ;
		sql +=	" WHERE I.user_idx = U.user_idx" ;
		sql +=	" AND I.inq_idx= ?";
		
		//DB 객체 
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		// 결과 저장할 변수
		String nick = null;
		
		try {
			// DB 작업
			ps = conn.prepareStatement(sql);
			ps.setInt(1, inq.getInq_idx());
			
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
	public int selectInqIdx() {
		
		// 다음 게시글 번호 조회 쿼리 
		String sql ="SELECT inquiry_seq.nextval FROM dual";
		
		// DB 객체 
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		// 결과 저장할 변수 
		
		int inq_idx = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				inq_idx = rs.getInt(1);
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
		
		
		return inq_idx;
	}
	@Override
	public void deleteInqList(String names) {
		String sql = "DELETE FROM inquiry WHERE inq_idx IN("+names+")";
	
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
	@Override
	public List<Inquiry> selectPagingMyList(Paging paging, int user_idx) {
		// 페이징 리스트 조회 쿼리
		String sql="";
		sql += "SELECT * FROM( ";
		sql += "SELECT rownum rnum, I.* FROM ( ";
		sql += "SELECT  inq_idx, (SELECT nickname FROM userinfo U WHERE U.user_idx = INQ.user_idx) nick,hit, " ;
		sql +=		"title, content,create_date,answer FROM inquiry INQ WHERE user_idx = ? ORDER BY inq_idx DESC ) I  ";
		sql += " ORDER BY rnum";
		sql += ") " ;
		sql += "WHERE rnum between ? AND ?";
			
				//DB 객체 생성 
				PreparedStatement ps = null;
				ResultSet rs = null;
				
				// 조회 결과 담을 list 생성 
				List<Inquiry> list = new ArrayList<>();
				
				try {
					// DB 작업 실행
					ps = conn.prepareStatement(sql);
					
					ps.setInt(1, user_idx);
					ps.setInt(2, paging.getStartNo());
					ps.setInt(3, paging.getEndNo());
					
					rs = ps.executeQuery();
					
					// 조회 결과 List에 담기
					while( rs.next()) {
						Inquiry inq = new Inquiry();
						
						// rs의 결과 DTO에 하나씩 저장하기
						inq.setRnum(rs.getInt("rnum"));
						inq.setInq_idx(rs.getInt("inq_idx"));
						inq.setWriter(rs.getString("nick"));
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
}
