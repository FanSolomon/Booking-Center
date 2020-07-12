package com.fansolomon.bookingService.entity.dto;

import java.io.Serializable;

/**
 * @author colin
 * @since 2020-07-11
 * @param <T>
 */
public class ResultDTO<T> implements Serializable {

    private T data;

    private boolean success;

    private String code;

    private String message;

    private String errorMsg;

    public ResultDTO() {
    }

    public ResultDTO(T data) {
        this.data = data;
        this.success = true;
    }

    public ResultDTO(String code, String errorMsg) {
        this.success = false;
        this.code = code;
        this.errorMsg = errorMsg;
    }

    public ResultDTO(String code, String message, String errorMsg) {
        this.success = false;
        this.code = code;
        this.message = message;
        this.errorMsg = errorMsg;
    }

    public ResultDTO(T data, boolean success, String code, String message, String errorMsg) {
        this.data = data;
        this.success = success;
        this.code = code;
        this.message = message;
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
