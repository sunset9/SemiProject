package service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Notice;
import dto.board.NoticeFile;
import utils.Paging;

public interface NoticeService {


	// ���� ������ ��� ���� 
	public int getCurPage(HttpServletRequest req);
	
	// ��ü �� �� ������ 
	public int getTotalCount();
	
	// ����¡�� ����Ʈ �θ��� DAO ȣ��
	public List<Notice> getPagingList(Paging paging);
	
	// �Խñ� �� ���� 
	public Notice view (Notice notice);
	
	// �Ķ���� �޾� ���� 
	public Notice getParam (HttpServletRequest res, HttpServletResponse resp);
	
	//�Խñ� ���� �ҷ�����
	public NoticeFile viewFile(Notice notice);

}
