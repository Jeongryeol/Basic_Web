package pojo.demo;

import java.util.List;
import java.util.Map;

/****************************************************************************************
 * [[ ������ �̵� ��� ]]
 * @�ۼ���	������
 * @�ۼ���	2018.08.10
 * @�����ǵ�
 * 		DAO�� ����� ȭ�鿡 �����ϱ� ����<br>
 * 		���� 3���� �ڷḦ "FrontController.java"�� �����Ѵ�
 * @�ڷ�
 * 		- isRedirect<br>
 * 		- path<br>
 * 		- datas
 * @����
 * 		- <b>boolean <u>isRedirect</u></b> <br>&nbsp;&nbsp;&nbsp;&nbsp;
 * 		 : true(sendRedirect ���) / false(forward ���)<br> 
 * 		- <b>String <u>path</u></b> <br>&nbsp;&nbsp;&nbsp;&nbsp;
 * 		 : ������ ������ url��� ( ���� | jsp )<br>
 * 		- <b>List <u>datas</u></b> <br>&nbsp;&nbsp;&nbsp;&nbsp;
 * 		 : ��ȸ���  
 ****************************************************************************************/
public class DemoPageCarrier {

	private	boolean	isRedirect	= false;//true : sendRedirect | false : foward 
	private	String	path		= null;	//�̵��� ��������� ( Servlet | JSP )
	private List<Map<String,Object>> datas = null;//��ȸ���
	private int	status	= -2;//���̵�,��й�ȣã�� ���°� �⺻����Ʈ�� -2����
	
	//������ �̵���� getter,setter
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	//������ ��� getter,setter
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	//��ȸ��� getter,setter
	public List<Map<String,Object>> getDatas(){
		return datas;
	}
	public void setDatas(List<Map<String,Object>> datas) {
		this.datas = datas;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
