package com.jdkhome.basic.common.exception;


import com.jdkhome.basic.common.enums.RespError;

/**
 * Created by jdk on 17/4/25.
 * 业务异常,必须包含错误码和错误信息，controller层直接返回其错误码和错误信息
 * 内部错误，不需要包含错误码的错误交给Exception来处理
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    //异常码
    private final Integer errorCode;

    //异常信息
    private final String errorMsg;

    public RespError getRespError() {
        return respError;
    }

    //返回给前端的错误信息
    private final RespError respError;

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public ServiceException() {
        super();
        errorMsg = null;
        errorCode = null;
        respError = null;
    }

    public ServiceException(RespError respError) {
        super();
        this.respError = respError;
        this.errorMsg = respError.getMsg();
        this.errorCode = respError.getCode();
    }


    public ServiceException(Integer errorCode, String message) {
        super(message);
        this.errorMsg = message;
        this.errorCode = errorCode;
        respError = null;
    }

    public ServiceException(Integer errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        respError = null;
        errorMsg = null;
    }

    public ServiceException(Integer errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorMsg = message;
        this.errorCode = errorCode;
        respError = null;
    }


}
