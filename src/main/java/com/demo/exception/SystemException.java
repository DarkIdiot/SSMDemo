package com.demo.exception;

/** 
 *
 * @author DarkIdiot-PC
 * @date 2016年3月9日 上午11:35:02
 */
@SuppressWarnings("serial")
public class SystemException extends Exception{
	protected int errorCode = 500;
	public SystemException() {
		super();
	}
	
	public SystemException(int errorCode) {
		super();
		if (errorCode >= 600) {
			this.errorCode = errorCode;
		}
	}

	public int getErrorCode() {
		return errorCode;
	}

	public SystemException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}

}
