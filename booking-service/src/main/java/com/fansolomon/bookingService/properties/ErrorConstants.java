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

    String USER_REGISTER_ERROR_CODE = "0004";
    String USER_REGISTER_ERROR_MESSAGE = "用户注册出错";

    String GET_OAUTH_TOKEN_ERROR_CODE = "0005";
    String GET_OAUTH_TOKEN_ERROR_MESSAGE = "获取token失败";

    String WRONG_USERNAME_OR_PASSWORD_CODE = "0006";
    String WRONG_USERNAME_OR_PASSWORD_MESSAGE = "用户名或密码错误";

    String ACCESS_TOKEN_EXPIRED_CODE = "0007";
    String ACCESS_TOKEN_EXPIRED_MESSAGE = "token失效";

    String INVALID_ACCESS_TOKEN_CODE = "0008";
    String INVALID_ACCESS_TOKEN_MESSAGE = "token校验异常";

    String REFRESH_TOKEN_ERROR_CODE = "0009";
    String REFRESH_TOKEN_ERROR_MESSAGE = "刷新token失败";

    String ACCESS_TOKEN_ERROR_CODE = "0010";
    String ACCESS_TOKEN_ERROR_MESSAGE = "access_token比对失败";

    // =========================校验权限部分错误码 0000~0099 end=========================
}
