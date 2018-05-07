package com.talent.base.exception;

/**
 * 2017年11月13日
 * 
 * @author fwp 系统异常
 */
public class SystemException extends RuntimeException {

	private static final long serialVersionUID = 3787730660315875183L;

	private Exception exception;

	private ErrorCode code;

	private String title = "系统出错了！";

	public SystemException(ErrorCode code, Exception e) {
		super(code.getMessage());
		this.code = code;
		this.exception = e;
	}

	public SystemException(ErrorCode code, String title, Exception e) {
		super(code.getMessage());
		this.code = code;
		this.exception = e;
		this.title = title;
	}

	public String getTitle() {
		if (null == title || "".equals(title)) {
			return "系统异常！";
		} else {
			return title;
		}
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public ErrorCode getCode() {
		return code;
	}

	public void setCode(ErrorCode code) {
		this.code = code;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
