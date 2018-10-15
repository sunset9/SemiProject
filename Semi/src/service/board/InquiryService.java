package service.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.board.InqFile;
import dto.board.Inquiry;
import utils.Paging;

public interface InquiryService {


	// ���� ������ ��� ���� 
	public int getCurPage(HttpServletRequest req);
	
	// ��ü �� �� ������ 
	public int getTotalCount();
	
	// ����¡�� ����Ʈ �θ��� DAO ȣ��
	public List<Inquiry> getPagingList(Paging paging);
	
	// �Խñ� �� ���� 
	public Inquiry view (Inquiry inq);
	
	// �Ķ���� �޾ƿ��� 
	public Inquiry getParam (HttpServletRequest req, HttpServletResponse resp);
	
	// �Խñ� �߰� �ϱ� 
	public void write(Inquiry inq);
	
	// �Խñ� ���� 
	public void update(Inquiry inq);
	
	// �Խñ� ���� 
	public void delete(Inquiry inq);
	
	// �Խñ� ���� �����ϱ� 
	public void deleteInqFile(InqFile file);
	
	//�Խñ� ���� �ҷ�����
	public InqFile viewFile(Inquiry inq);
	
	// �Խñ� ���� �ֱ� 
	public void insertFile(InqFile file);

	
}
