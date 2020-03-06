package cn.zhangjd.ex;
/**
 * 手机号不可用异常
 * @author Administrator
 *
 */
public class PhoneNotAvailableException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public PhoneNotAvailableException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PhoneNotAvailableException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public PhoneNotAvailableException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PhoneNotAvailableException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PhoneNotAvailableException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
