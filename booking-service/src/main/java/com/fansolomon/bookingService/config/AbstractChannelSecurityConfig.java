package com.fansolomon.bookingService.config;

import com.fansolomon.bookingService.properties.BcServiceConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author Colin
 * @since 2020-07-12
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    protected AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler myAuthenticationFailureHandler;

    @Override
    public void configure(WebSecurity web) throws Exception {
        //web.ignoring().antMatchers("/**");
        web.ignoring().antMatchers(BcServiceConstants.DEFAULT_UNAUTHENTICATION_URL,
                BcServiceConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                BcServiceConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*");
    }

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(BcServiceConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(BcServiceConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler);
    }
}