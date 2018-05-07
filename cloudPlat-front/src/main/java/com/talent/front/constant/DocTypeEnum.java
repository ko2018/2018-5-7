package com.talent.front.constant;

public enum DocTypeEnum {
    ORIGINAL("c259eb3860aa4ea79a3cd4ea4a1c17ea", "未标准化体检数据"), CLEANED("25a45c4f641b4fd2a25022dbce8683fc",
            "标准化后体检数据"), EMR("d52087be57b1485491dd178443a23965",
                    "EMR"), UNDO("c51fb1f4372746efaeb3558dd8a4e95e", "其他形式");

    private String code;

    private String message;

    private DocTypeEnum(String code, String message) {
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
