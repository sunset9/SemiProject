package dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dto.board.InqFile;
import dto.board.Inquiry;
import utils.DBConn;


public class InqFileDaoImpl implements InqFileDao {

	
	// DB 연결 객체 
	private Connection conn = DBConn.getConnection();
	
	@Override
	public void insert(InqFile file) {
		// 첨부파일 추가 쿼리
		String sql="";
		sql +="INSERT INTO inquiry_file (ifile_idx, stored_name, origin_name, inq_idx, file_size)";
		sql +=	"VALUES(INQUIRY_FILE_SEQ.nextval,?,?,?,?)";
		
		//DB 객체
		PreparedStatement ps = null;
		
		try {
			conn.setAutoCommit(false);
			
			//DB작업 
			ps = conn.prepareStatement(sql);
			ps.setString(1, file.getStored_name());
			ps.setString(2, file.getOrigin_name());
			ps.setInt(3, file.getInq_idx());
			ps.setLong(4, file.getFile_size());
			
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
	public List<InqFile> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InqFile selectByFileno(InqFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Inquiry inq) {
		// 첨부 파일 지우기 
		String sql = "";
		sql +="DELETE inquiry_file WHERE inq_idx =?";
		
		//DB 객체 
		PreparedStatement ps = null;
		
		try {
			conn.setAutoCommit(false);
			
			//DB작업 
			ps = conn.prepareStatement(sql);
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
	public InqFile selectByInqIdx(Inquiry inq) {
		// 게시글에 관련된 첨부파일 조회
		String sql ="";
		sql+="SELECT * FROM inquiry_file ";
		sql+="WHERE inq_idx= ? " ;
		sql+="ORDER BY ifile_idx";
		
		// DB 객체 
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		InqFile inqFile = new InqFile();
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, inq.getInq_idx());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				inqFile.setiFile_idx(rs.getInt("iFile_idx"));
				inqFile.setInq_idx(rs.getInt("inq_idx"));
				inqFile.setOrigin_name(rs.getString("origin_name"));
				inqFile.setStored_name(rs.getString("stored_name"));
				inqFile.setFile_size(rs.getLong("file_size"));
				inqFile.setCreate_date(rs.getDate("create_date"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				//DB객체 닫기
				if(ps!=null)	ps.close();
				if(rs!=null)	rs.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return inqFile;
	}


	@Override
	public void deleteInqListFile(String names) {
		String sql="DELETE FROM inquiry_file WHERE inq_idx IN("+names+")";
		
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
