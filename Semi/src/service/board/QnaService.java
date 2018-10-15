package service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.Qna;
import dto.board.QnaFile;
import utils.Paging;

public interface QnaService {


	// ���� ������ ��� ���� 
	public int getCurPage(HttpServletRequest req);
	
	// ��ü �� �� ������ 
	public int getTotalCount();
	
	// ����¡�� ����Ʈ �θ��� DAO ȣ��
	public List<Qna> getPagingList(Paging paging);
	
	// �Խñ� �� ���� 
	public Qna view (Qna qna);
		
	// �Ķ���� �޾� ���� 
	public Qna getParam (HttpServletRequest res, HttpServletResponse resp);
	
	//�Խñ� ���� �ҷ�����
	public QnaFile viewFile(Qna qna);
	
}
