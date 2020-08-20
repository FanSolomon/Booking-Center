package com.fansolomon.bookingService.authorize;

import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * 个性化权限控制，针对用户角色url控制权限
 * @author Colin
 * @since 2020-08-20
 */
@Component
@Order(Integer.MAX_VALUE)
public class BookingServiceAuthorizeConfigProvider implements AuthorizeConfigProvider {
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        // TODO 判断是否有权限
//        config.anyRequest().access("");
    }
}
