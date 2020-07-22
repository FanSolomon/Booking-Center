package com.fansolomon.bookingService.properties;

/**
 * @author colin
 * @since 2020-07-11
 * public接口中属性默认为public static final
 */
public interface ErrorConstants {

    // =========================校验权限部分错误码 0000~0099 start=========================
    String AUTHENTICATION_FAILURE_CODE = "0000";
    String AUTHENTICATION_FAILURE_MESSAGE = "权限校验失败";

    String TOKEN_NOT_EXIST_CODE = "0001";
    String TOKEN_NOT_EXIST_MESSAGE = "请求头中token不存在";

    String LOGIN_PARAM_ERROR_CODE = "0002";
    String LOGIN_PARAM_ERROR_MESSAGE = "登录参数异常";

    String USER_NOT_FOUND_CODE = "0003";
    String USER_NOT_FOUND_MESSAGE = "用户不存在";

    // =========================校验权限部分错误码 0000~0099 end=========================
}
