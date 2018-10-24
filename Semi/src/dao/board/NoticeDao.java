package dao.board;

import java.util.List;

import dto.board.Notice;
import utils.Paging;

public interface NoticeDao {

	// Notice ��ü ����¡�Ͽ� ����Ʈ�� ��ȸ 
	public List<Notice> selectPagingList(Paging paging);
	
	// ��ü �Խñ� �� ��ȸ
	public int selectCntAll(String search) ;
		
	// notice_idx�� �ϳ��� Notice ��ȸ�ϱ�
	public Notice selectInqByInqIdx (Notice notice);
	
	// ��ȸ�� ��ȸ�� �ø���
	public void updateHit(Notice notice);
	
	// �Խù� ��� �ϱ�
	public void insert(Notice notice);
	
	// �Խù� ���� �ϱ�
	public void delete(Notice notice);
	
	// �Խù� ���� �ϱ�
	public void update(Notice notice);
}
