package com.talent.base.exception;

public class GlobalException extends Exception {
	private static final long serialVersionUID = -366816270035670512L;

	private String errorCode = "0000";

	private String data = "";

	public GlobalException(ErrorCode ec) {
		this.errorCode = ec.getCode();
		this.data = ec.getMessage();
	}

	public GlobalException(String errorCode, String data) {
		this.errorCode = errorCode;
		this.data = data;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getData() {
		return data;
	}
}
