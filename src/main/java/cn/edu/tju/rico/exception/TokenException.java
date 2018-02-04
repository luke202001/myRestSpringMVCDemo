package cn.edu.tju.rico.exception;

/**
 * Title: �Զ����RuntimeException
 * Description:Token����ʱ�׳�
 * @author rico
 * @created 2017��7��4�� ����4:56:44
 */
public class TokenException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String msg;

	public TokenException(String msg) {
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
