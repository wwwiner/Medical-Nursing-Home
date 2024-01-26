package com.neuedu.springboot.result;

/**
 * Description:
 * Param:
 * return:
 * Author:
 * Date:2023/4/18
 */

public enum ResultCodeEnum {

    CAU_ERROR(false, 501, "计算错误"),

    SUCCESS(true, 200, "成功"),

    UNKNOWN_REASON(false, 500, "未知错误");

    private final Boolean success;

    private final Integer code;

    private final String message;

    ResultCodeEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ResultCodeEnum{" + "success=" + success + ", code=" + code + ", message='" + message + '\'' + '}';
    }


}

