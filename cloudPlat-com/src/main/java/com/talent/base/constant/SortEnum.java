package com.talent.base.constant;

public enum SortEnum {
	/**
	 * 升序
	 */
	ASC(0),
	/**
	 * 降序
	 */
	DESC(1);

	private int value = 0;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	private SortEnum(int value) {
		this.value = value;
	}
}
