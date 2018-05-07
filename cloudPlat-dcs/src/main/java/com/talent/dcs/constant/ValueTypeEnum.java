package com.talent.dcs.constant;

public enum ValueTypeEnum {
	NUM("e77b1c93f5f44a0b832fd68a005fc3a0", "数值型"), ENUM("d7bb54baedf643359791f04caa9ca132", "枚举型"), RANK(
			"9a7271d5045044828ffd84c4b0e9cdb0",
			"离散型"), TEXT("b07f9dbff8184429ab9ba438e6ecb723", "文本型"), UNDO("a5b493707b264c50b4f395b756e3d59b", "其他");

	private String code;
	private String message;

	private ValueTypeEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message == null ? "" : message;
	}

}
