package com.talent.base.constant;

public enum UserStatus {
	/**
	 * 有效
	 */
	LOGIN("1"),
	/**
	 * 禁止登陆
	 */
	NOTLOGIN("0"); // 调用构造函数来构造枚举项

	private String value;

	private UserStatus(String value) { // 必须是private的，否则编译错误
		this.value = value;
	}

	public String value() {
		return this.value;
	}
}
