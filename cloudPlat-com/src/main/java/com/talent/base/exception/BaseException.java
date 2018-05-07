package com.talent.base.exception;

import java.io.Serializable;

/**
 * 2017年11月13日
 * 
 * @author fwp 异常基类
 */
public abstract class BaseException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1311416870635146320L;
	protected Exception exception;
	protected ErrorCode code;
	protected String title = "系统出错了！";

	protected abstract String getTitle();

	/**
	 * @param code
	 */
	public BaseException(ErrorCode code) {
		super(code.getMessage());
		this.code = code;
	}

	/**
	 * getException
	 * 
	 * @return
	 */
	public Exception getException() {
		return exception;
	}

	/**
	 * setException
	 * 
	 * @param exception
	 *            Exception
	 */
	public void setException(Exception exception) {
		this.exception = exception;
	}

	/**
	 * getCode
	 * 
	 * @return
	 */
	public ErrorCode getCode() {
		return code;
	}

	/**
	 * setCode
	 * 
	 * @param code
	 *            error code
	 */
	public void setCode(ErrorCode code) {
		this.code = code;
	}

	/**
	 * setTitle
	 * 
	 * @param title
	 *            title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

}
