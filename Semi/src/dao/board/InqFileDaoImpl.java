package dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	public void delete(InqFile file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InqFile selectByInqIdx(Inquiry inq) {
		// TODO Auto-generated method stub
		return null;
	}

}
