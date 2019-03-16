package com.jdkhome.basic.common.response;


import com.jdkhome.basic.common.enums.RespError;

/**
 * Created by jdk on 2017/4/6.
 */
public class ApiResponse {

    private int code;
    private String msg;
    private Object data;

    public ApiResponse() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ApiResponse(int code) {
        this.code = code;
        this.msg = (code == 0) ? "success" : msg;
        this.data = "";
    }

    public ApiResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = "";
    }

    public ApiResponse(String msg, String data) {
        // 默认code都是0
        this.code = 0;
        this.msg = msg;
        this.data = data;
    }

    /**
     *  返回成功响应
     */
    public static ApiResponse successResponse(Object data) {
        ApiResponse response = new ApiResponse(0);
        response.data = data;
        return response;
    }

    /**
     *  返回指定错误类型的响应
     */
    public static ApiResponse responseWithRespError(RespError respError) {
        ApiResponse response =  new ApiResponse(0);
        response.code = respError.getCode();
        response.msg = respError.getMsg();
        return response;
    }




}
