package dao.contents;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.board.Inquiry;
import dto.plan.Plan;
import utils.DBConn;
import utils.Paging;

public class ContentsDaoImpl implements ContentsDao{
	
	private Connection conn = DBConn.getConnection();
	
	//리스트 검색하기
	@Override
	public List<Plan> selectList(String category, String searchValue) {
		System.out.println("contentsDaoImpl:"+category);
		System.out.println("contentsDaoImpl:"+searchValue);
		
		//String sql = "SELECT * FROM PLANNER WHERE ? LIKE '%' || ? || '%'";
		String sql = "SELECT * FROM PLANNER where INSTR("+category+",?) > 0";
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Plan> planList = new ArrayList<>();
		
		try {
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, searchValue);
			
			System.out.println(1);
			
			rs = ps.executeQuery();
			
			System.out.println(2);
			
			while(rs.next()) {
				Plan plan = new Plan();
				System.out.println(3);
				plan.setPlan_idx(rs.getInt("PLAN_IDX"));
				plan.setUser_idx(rs.getInt("USER_IDX"));
				plan.setStart_date(rs.getDate("START_DATE"));
				plan.setEnd_date(rs.getDate("END_DATE"));
				plan.setTitle(rs.getString("TITLE"));
				plan.setTraveled(rs.getInt("TRAVELED"));
				plan.setOpened(rs.getInt("OPENED"));
				plan.setDistance(rs.getInt("DISTANCE"));
				plan.setCreate_date(rs.getDate("CREATE_DATE"));
				plan.setBannerURL(rs.getString("BANNERURL"));
				
				System.out.println("ContentsDaoImpl plan : "+plan);
				
				planList.add(plan);
				
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
		return planList;
	}

	@Override
	public int selectCntAll(int searchType, String search) {
		System.out.println("검색어 : "+search);
		System.out.println("검색타입 : "+searchType);
		if(searchType == 1) {
			String type = "title";
		} else if(searchType == 2) {
			String type = "nickname";
		}
		
		
		
		// 전체 게시글 수 조회
		String sql = "SELECT COUNT(*) FROM planner";

		if (search != null && !"".equals(search)) {
			sql += " WHERE ? LIKE '%" + search + "%'";
		}

		// DB객체 생성
		PreparedStatement ps = null;
		ResultSet rs = null;

		// 조회 결과 저장할 변수 생성 =
		int cnt = 0;

		try {
			ps = conn.prepareStatement(sql);
			ps.setLong(1, searchType);
			rs = ps.executeQuery();

			rs.next();

			// 조회 결과중 첫 번째 컬럼값 가져오기
			cnt = rs.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnt;
	}

	//페이징 된 Plan 리스트 조회
	@Override
	public List<Plan> selectPagingList(Paging paging) {
		// 페이징 리스트 조회 쿼리
		String sql = "SELECT * FROM( SELECT rownum rnum, PL.* FROM ( SELECT plan_idx, P.user_idx, " ;
			   sql +=	"(SELECT nickname FROM userinfo U WHERE U.user_idx = P.user_idx )nickname  " ;
			   sql +=", start_date, end_date, title, traveled, opened, distance, bannerurl, create_date  " ;
			   sql +="FROM planner P  ORDER BY user_idx DESC " ; 
			   sql +=") PL  " ;
			   
			   // searchType 1이면 제목으로 조회
			   if(paging.getSearchType()==1) {
			   sql +="WHERE title  " ; 
			   }else if (paging.getSearchType()==2) {
				   // searchType 2이면 닉네임으로 조회
				   sql+= "WHERE nickname";
			   }
			   
			   if(paging.getSearch()!=null && !"".equals(paging.getSearch())) {
					sql += "  LIKE '%"+paging.getSearch()+"%'";
				}
			   sql +="ORDER BY rnum   ) WHERE rnum between ? AND ?";
		

		// DB 객체 생성
		PreparedStatement ps = null;
		ResultSet rs = null;

		// 조회 결과 담을 list 생성
		List<Plan> list = new ArrayList<>();

		try {
			// DB 작업 실행
			ps = conn.prepareStatement(sql);

			ps.setInt(1, paging.getStartNo());
			ps.setInt(2, paging.getEndNo());

			rs = ps.executeQuery();

			// 조회 결과 List에 담기
			while (rs.next()) {
				Plan p = new Plan();
				
				
			// rs의 결과 DTO에 하나씩 저장하기
				p.setPlan_idx(rs.getInt("plan_idx"));
				p.setUser_idx(rs.getInt("user_idx"));
				p.setStart_date(rs.getDate("start_date"));
				p.setEnd_date(rs.getDate("end_date"));
				p.setDistance(rs.getInt("distance"));
				p.setOpened(rs.getInt("opened"));
				p.setTitle(rs.getString("title"));
				p.setBannerURL(rs.getString("bannerurl"));
				p.setNick(rs.getString("nickname"));
				
				p.setCreate_date(rs.getDate("create_date"));
				

//				// 조회 결과 List에 넣기
				list.add(p);

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

}
