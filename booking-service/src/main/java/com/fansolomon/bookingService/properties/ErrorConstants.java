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

    // =========================校验权限部分错误码 0000~0099 end=========================
}
