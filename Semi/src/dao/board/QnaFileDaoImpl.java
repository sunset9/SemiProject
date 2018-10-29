package dao.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dto.board.NoticeFile;
import dto.board.Qna;
import dto.board.QnaFile;
import utils.DBConn;

public class QnaFileDaoImpl implements  QnaFileDao {

	private Connection conn = DBConn.getConnection();
	@Override
	public void insert(QnaFile file) {
		String sql="";
		sql +="INSERT INTO qna_file (qfile_idx, stored_name, origin_name, qna_idx, file_size)";
		sql +=	"VALUES(QNA_FILE_SEQ.nextval,?,?,?,?)";
		
		//DB 객체
		PreparedStatement ps = null;
		
		try {
			conn.setAutoCommit(false);
			
			//DB작업 
			ps = conn.prepareStatement(sql);
			ps.setString(1, file.getStored_name());
			ps.setString(2, file.getOrigin_name());
			ps.setInt(3, file.getQna_idx());
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
	public List<QnaFile> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QnaFile selectByFileno(int fileno) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public QnaFile selectFilebyQna(Qna qna) {
		// 게시글에 관련된 첨부파일 조회
				String sql ="";
				sql+="SELECT * FROM notice_file ";
				sql+="WHERE notice_idx= ? " ;
				sql+="ORDER BY nfile_idx";
						
				// DB 객체 
				PreparedStatement ps = null;
				ResultSet rs = null;
						
				NoticeFile noticeFile = new NoticeFile();
						
				try {
					ps = conn.prepareStatement(sql);
					ps.setInt(1, notice.getNotice_idx());
							
					rs = ps.executeQuery();
							
					while(rs.next()) {
						noticeFile.setnFile_idx(rs.getInt("nFile_idx"));
						noticeFile.setNotice_idx(rs.getInt("notice_idx"));
						noticeFile.setOrigin_name(rs.getString("origin_name"));
						noticeFile.setStored_name(rs.getString("stored_name"));
						noticeFile.setFile_size(rs.getLong("file_size"));
						noticeFile.setCreate_date(rs.getDate("create_date"));
						
					}
					System.out.println(noticeFile);
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
						
				return noticeFile;
	}

	@Override
	public void delete(Qna qna) {
		// 첨부 파일 지우기 
		String sql = "";
		sql +="DELETE qna_file WHERE qna_idx =?";
				
		//DB 객체 
		PreparedStatement ps = null;
		 		
		try {
			conn.setAutoCommit(false);
		 			
		 	//DB작업 
			ps = conn.prepareStatement(sql);
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
	public void deleteQnaListFile(String names) {
		String sql="DELETE FROM qna_file WHERE qna_idx IN("+names+")";
		
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
