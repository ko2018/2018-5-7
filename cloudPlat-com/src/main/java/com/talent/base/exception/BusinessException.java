package com.talent.base.exception;

/**
 * 系统业务异常 2017年11月13日
 * 
 * @author fwp
 *
 */
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 8109303352556055331L;

	private ErrorCode code;

	private String title;

	public BusinessException(ErrorCode code, String title, Throwable e) {
		super(code.getMessage(), e);
		this.code = code;
		this.title = title;
	}

	public BusinessException(ErrorCode code, Throwable e) {
		super(e);
		this.code = code;
		this.title = "系统出错了！";
	}

	public BusinessException(ErrorCode code) {
		this.code = code;
		this.title = "系统出错了！";
	}

	public ErrorCode getCode() {
		return code;
	}

	public void setCode(ErrorCode code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
