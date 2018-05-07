package com.talent.base.constant;

public enum Sex {
	M("男"), F("女");
	private String sexName;

	private Sex(String sexName) {
		this.sexName = sexName;
	}

	public String getSexName() {
		return sexName;
	}

}
