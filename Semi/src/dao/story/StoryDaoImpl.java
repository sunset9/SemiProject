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
import service.plan.PlanService;
import service.stroy.StoryService;
import service.stroy.StoryServiceImpl;
import utils.CalcDate;
import utils.DBConn;

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
				" WHERE plan_idx = ?" + 
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
		// TODO Auto-generated method stub
		return null;
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
			ps = conn.prepareStatement(sql);
			
			String content = story.getContent();
			
			ps.setInt(1,story.getStory_idx());
			ps.setInt(2,story.getPlan_idx());
			ps.setInt(3,story.getTtb_idx());
			ps.setInt(4,story.getUser_idx());
			ps.setString(5, content);
			
			ps.executeUpdate();
			
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
	public void update(Story story) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Story story) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteList(Plan plan) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int SelectCntAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Comment> selectCommentByStoryNo(Story story) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment selectCommentByCommentIdx(Comment cmt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCommentList(Story story) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertComment() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteComment() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateComment() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Comment selectCommentByContent(Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int SelectStoryIdx() {
		// TODO Auto-generated method stub
		return 0;
	}


}
