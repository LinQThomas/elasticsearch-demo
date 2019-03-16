package com.jdkhome.basic.common.enums;

/**
 * Created by jdk on 17/4/24.
 * 错误枚举，错误码和错误信息定义到这里,注意，这是面向(视图层)用户的错误提示
 */
public enum RespError {

    RESP_ERROR_PARAMER_ERROR(300, "参数错误");

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    RespError(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
