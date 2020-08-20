package com.fansolomon.bookingService.authorize;

import com.fansolomon.bookingService.properties.BcServiceConstants;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * 通用权限控制 来自BcServiceSecurityConfig
 * @author Colin
 * @since 2020-08-20
 */
@Component
@Order(Integer.MIN_VALUE)
public class OauthAuthorizeConfigProvider implements AuthorizeConfigProvider {
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(BcServiceConstants.DEFAULT_UNAUTHENTICATION_URL,
                BcServiceConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                BcServiceConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*", "/auth/*").permitAll();
    }
}
