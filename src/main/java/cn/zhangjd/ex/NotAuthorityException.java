package cn.zhangjd.ex;

public class NotAuthorityException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public NotAuthorityException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotAuthorityException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NotAuthorityException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NotAuthorityException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NotAuthorityException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
