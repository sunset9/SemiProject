package dao.story;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.account.AccountDao;
import dao.account.AccountDaoImpl;
import dto.plan.Plan;
import dto.story.Comment;
import dto.story.Story;
import dto.timetable.Timetable;
import service.plan.PlanService;
import service.stroy.StoryService;
import service.stroy.StoryServiceImpl;
import utils.CalcDate;
import utils.DBConn;
import utils.Paging;

public class StoryDaoImpl implements StoryDao{
	
	private Connection conn = DBConn.getConnection();
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	@Override
	public List<Story> selectAllByPlanNo(Plan plan) {
		
		String sql = 
				"SELECT" + 
				" s.story_idx," + 
				" s.plan_idx," +
				" s.ttb_idx," +
				" s.user_idx," +
				" s.content," +
				" s.create_date," +
				" TO_CHAR(ttb.start_time,'yyyy-mm-dd') travel_day," + 
				" TO_CHAR(ttb.start_time,'HH24:mi') start_time," + 
				" TO_CHAR(ttb.end_time,'HH24:mi') end_time," + 
				" loc.place_name" + 
				" FROM" + 
				" story s" + 
				" LEFT JOIN planner p ON s.plan_idx = p.plan_idx" + 
				" LEFT JOIN timetable ttb ON s.ttb_idx = ttb.ttb_idx" + 
				" LEFT JOIN location loc ON ttb.loc_idx = loc.loc_idx" + 
				" WHERE s.plan_idx = ?" + 
				" ORDER BY" + 
				"   ttb.start_time";
		
		List<Story> sList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, plan.getPlan_idx());
			rs = ps.executeQuery();

			while(rs.next()) {
				Story story = new Story();
				
				story.setStory_idx(rs.getInt("story_idx"));
				story.setPlan_idx(rs.getInt("plan_idx"));
				story.setTtb_idx(rs.getInt("ttb_idx"));
				story.setUser_idx(rs.getInt("user_idx"));
				story.setContent(rs.getString("content"));
				story.setCreate_date(rs.getDate("create_date"));
				story.setStart_time(rs.getString("start_time"));
				story.setEnd_time(rs.getString("end_time"));
				story.setPlace_name(rs.getString("place_name"));
				story.setTravel_day(rs.getString("travel_day"));
				
				sList.add(story);
		
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				try {
					if (ps != null) {
						ps.close();
					}
					if (rs != null) {
						rs.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	
		return sList;
	}
	
	@Override
	public Story selectStoryByTtbIdx(Story story) {
		String sql = "SELECT story_idx, plan_idx, ttb_idx, user_idx, content"
				+ " FROM story"
				+ " WHERE ttb_idx=?";
		Story storyRes = new Story();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, story.getTtb_idx());
			
			rs = ps.executeQuery();
			while(rs.next()) {
				storyRes.setStory_idx(rs.getInt("story_idx"));
				storyRes.setPlan_idx(rs.getInt("plan_idx"));
				storyRes.setTtb_idx(rs.getInt("ttb_idx"));
				storyRes.setUser_idx(rs.getInt("user_idx"));
				storyRes.setContent(rs.getString("content"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) ps.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return storyRes;
	}
	
	@Override
	public Story selectStoryByStoryIdx(Story story) {
		String sql ="";
		sql += "SELECT * FROM story where story_idx=?";
		
		try {
			conn.setAutoCommit(false);
			ps= conn.prepareStatement(sql);
			
			ps.setInt(1, story.getStory_idx());
			
			rs = ps.executeQuery();

			while(rs.next()) {
				story.setStory_idx(rs.getInt("story_idx"));
				story.setPlan_idx(rs.getInt("plan_idx"));
				story.setTtb_idx(rs.getInt("ttb_idx"));
				story.setUser_idx(rs.getInt("user_idx"));
				story.setContent(rs.getString("content"));
				story.setCreate_date(rs.getDate("create_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) ps.close();
				if (rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		 
		
		return story;
	}

	@Override
	public void updateHit(Story story) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Story story) {
		String sql = "";
		sql += "INSERT INTO STORY(story_idx, plan_idx, ttb_idx, user_idx, content)";
		sql	+= " VALUES(?,?,?,?,?)";
		
		try {
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1,story.getStory_idx());
			ps.setInt(2,story.getPlan_idx());
			ps.setInt(3,story.getTtb_idx());
			ps.setInt(4,story.getUser_idx());
			ps.setString(5, story.getContent());
			
			ps.executeUpdate();
			
			conn.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
				try {
					if(ps != null)	ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	
		}
		
	}

	@Override
	public void update(Story story) {
		
		String sql = "UPDATE story SET content = ? where ttb_idx = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, story.getContent());
			ps.setInt(2, story.getTtb_idx());
			ps.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(ps!= null) {
				ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		}
		
	}

	@Override
	public void delete(Story story) {
		String sql = "Delete from story where story_idx = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, story.getStory_idx());
			ps.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
				try {
					if(ps!= null) {
					ps.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
		
		
	}

	@Override
	public void delete(Plan plan, List<Timetable> ttbList) {
		String sql = "DELETE story"
				+ " WHERE plan_idx=?";
		
		// 저장하려는 ttb_idx를 가지고 있지 않은 스토리 삭제하는 쿼리
		if(ttbList.size() != 0) {
			sql += " AND ttb_idx NOT IN (";
			for(int i = 0; i < ttbList.size() -1; i++) {
				sql += ttbList.get(i).getTtb_idx() + ",";
			}
			sql += ttbList.get(ttbList.size()-1).getTtb_idx() +")";
		}
		
		try {
			
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, plan.getPlan_idx());
			
			ps.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
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
	public void deleteStoryListByPlanIdx(Plan plan) {
		String sql = "DELETE story"
				+ " WHERE plan_idx=?";
		
		try {
			conn.setAutoCommit(false);
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, plan.getPlan_idx());
			
			ps.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
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
	public int SelectCntAll() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public Comment selectCommentByCommentIdx(Comment cmt) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean deleteComment(Comment comment) {
		
		String sql = "DELETE FROM STORY_COMMENT WHERE comm_idx = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, comment.getComm_idx());
			ps.executeQuery();
			
			conn.commit();
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
		return true;
	}

	@Override
	public void updateComment(Comment comment) {
		
	}

	@Override
	public Comment selectCommentByContent(Comment comment) {
		return null;
	}

	@Override
	public int SelectStoryIdx() {
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "SELECT story_seq.nextval";
		sql += " FROM dual";
		
		//DB 객체
		//게시글번호
		int storyidx = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			//결과 담기
			while(rs.next()) {
				storyidx = rs.getInt(1);
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
		return storyidx;
}


	@Override
	public void deleteListByTtbIdx(Timetable tb) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Comment> selectCommentByPlanIdx(Plan plan) {
		
		String sql = "select * from STORY_COMMENT where plan_idx = ? order by create_date";
		List<Comment> CommentList = new ArrayList<>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, plan.getPlan_idx());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Comment cmt = new Comment();
				
				cmt.setComm_idx(rs.getInt("comm_idx"));
				cmt.setPlan_idx(rs.getInt("plan_idx"));
				cmt.setTtb_idx(rs.getInt("ttb_idx"));
				cmt.setStory_idx(rs.getInt("story_idx"));
				cmt.setUser_idx(rs.getInt("user_idx"));
				cmt.setContent(rs.getString("story_comm"));
				cmt.setCreate_date(rs.getDate("create_date"));
				
				CommentList.add(cmt);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		
		return CommentList;
	}

	@Override
	public void deleteCommentListByStoryIdx(Story story) {
	String sql = "DELETE FROM STORY_COMMENT WHERE story_idx = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, story.getStory_idx());
			ps.executeQuery();
			
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
	}

	@Override
	public void deleteCommentListByPlanIdx(Plan plan) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCommentListByTtbIdx(Timetable tb) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertComment(Comment comment) {
		
		String sql = "";
		sql += "INSERT INTO STORY_COMMENT(comm_idx,story_idx, plan_idx, ttb_idx, user_idx, story_comm)";
		sql	+= " VALUES(?,?,?,?,?,?)";
		    
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1,comment.getComm_idx());
			ps.setInt(2,comment.getStory_idx());
			ps.setInt(3,comment.getPlan_idx());
			ps.setInt(4,comment.getTtb_idx());
			ps.setInt(5, comment.getUser_idx());
			ps.setString(6, comment.getContent());
			
			ps.executeUpdate();
			
			conn.commit();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
				try {
					if(ps != null) {
						ps.close();
					}
					
					if(rs != null){
						rs.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
		}
		
	}

	@Override
	public int selectCommentIdx() {
		//다음 게시글 번호 조회 쿼리
		String sql = "";
		sql += "SELECT comment_seq.nextval";
		sql += " FROM dual";
		
		//DB 객체
		//게시글번호
		int commentidx = 0;
		
		try {
			//DB작업
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			//결과 담기
			while(rs.next()) {
				commentidx = rs.getInt(1);
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
		return commentidx;
	}

	@Override
	public int selectCntComm(Story story) {
		
		String sql = "SELECT COUNT(1) FROM STORY_COMMENT WHERE story_idx =?";
		
		int cnt = 0;
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, story.getStory_idx());
			
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				cnt = rs.getInt(1);	
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		return cnt;
	}

	@Override
	public int selectCmtCnt(String search) {
		String sql="";
		sql +=  "SELECT COUNT (*) FROM (";
		sql += "SELECT * FROM( ";
		sql += "SELECT rownum rnum, CL.* FROM ( ";
		sql+= "SELECT S.comm_idx, S.plan_idx, S.ttb_idx,S.story_idx, ";
		sql+= "		(SELECT nickname FROM userinfo U WHERE U.user_idx=S.user_idx) nick ,";
		sql+= "		S.story_comm, l.place_name, S.create_date FROM story_comment S";
		sql+= "			INNER JOIN timetable T";
		sql+= "    			ON s.plan_idx = t.plan_idx "; 
		sql+= "    			AND s.ttb_idx = t.ttb_idx";  
		sql+= "			INNER JOIN location L";  
		sql+= "    		ON t.loc_idx = l.loc_idx ORDER BY create_date DESC ) CL";
		
		   if(search!=null && !"".equals(search)) {
			    
			  sql+= " WHERE story_comm";
   		      sql+= " LIKE '%"+search+"%'";
			
		   }
		   
		   sql+= " ORDER BY rnum";
		   sql+= ") )";
		   
		// DB 객체 생성
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
	public List<Comment> selectCmtPagingList(Paging paging) {
		
		String sql="";
		sql += "SELECT * FROM( ";
		sql += "SELECT rownum rnum, CL.* FROM ( ";
		sql+= "SELECT S.comm_idx, S.plan_idx, S.ttb_idx,S.story_idx, ";
		sql+= "		(SELECT nickname FROM userinfo U WHERE U.user_idx=S.user_idx) nick ,";
		sql+= "		S.story_comm, l.place_name, S.create_date FROM story_comment S";
		sql+= "			INNER JOIN timetable T";
		sql+= "    			ON s.plan_idx = t.plan_idx "; 
		sql+= "    			AND s.ttb_idx = t.ttb_idx";  
		sql+= "			INNER JOIN location L";  
		sql+= "    		ON t.loc_idx = l.loc_idx ORDER BY create_date DESC ) CL";
		
		   if(paging.getSearch()!=null && !"".equals(paging.getSearch())) {
			    
			  sql+= " WHERE story_comm";
   		      sql+= " LIKE '%"+paging.getSearch()+"%'";
			
		   }
		   
		   sql+= " ORDER BY rnum";
		   sql+= ") WHERE rnum between ? AND ?";
		   
		// DB 객체 생성
			PreparedStatement ps = null;
			ResultSet rs = null;

			// 조회 결과 담을 list 생성
			List<Comment> list = new ArrayList<>();

			try {
				// DB 작업 실행
				ps = conn.prepareStatement(sql);

				ps.setInt(1, paging.getStartNo());
				ps.setInt(2, paging.getEndNo());

				rs = ps.executeQuery();

				// 조회 결과 List에 담기
				while (rs.next()) {
					Comment comm = new Comment();
					
					
				// rs의 결과 DTO에 하나씩 저장하기
					comm.setComm_idx(rs.getInt("comm_idx"));
					comm.setPlan_idx(rs.getInt("plan_idx"));
					comm.setPlace_name(rs.getString("place_name"));
					comm.setStory_idx(rs.getInt("story_idx"));
					comm.setNickname(rs.getString("nick"));
					comm.setContent(rs.getString("story_comm"));
					comm.setCreate_date(rs.getDate("create_date"));
					

//					// 조회 결과 List에 넣기
					list.add(comm);

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					// DB객체 닫기
					if (rs != null)
						rs.close();
					if (ps != null)
						ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			// 결과 반환
		return list;
	}

	@Override
	public void deleteListComm(String names) {
		String sql="DELETE FROM story_comment WHERE comm_idx IN("+names+")";
		
		// DB 객체 
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
		}finally {
			try {
				if(ps!=null)	ps.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
