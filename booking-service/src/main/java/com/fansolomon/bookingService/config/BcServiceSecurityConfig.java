package com.fansolomon.bookingService.config;

import com.fansolomon.bookingService.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.fansolomon.bookingService.filter.TokenCheckFilter;
import com.fansolomon.bookingService.properties.BcServiceConstants;
import com.fansolomon.bookingService.properties.SecurityProperties;
import com.fansolomon.bookingService.validate.code.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author colin
 * @since 2020-07-11
 */
//web应用安全配置的适配器
@Configuration
@EnableWebSecurity
public class BcServiceSecurityConfig extends AbstractChannelSecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private TokenCheckFilter tokenCheckFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        //加密方法，可以用自己的加密方法（实现encode matches方法）
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {

        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        //配置数据源
        tokenRepository.setDataSource(dataSource);
        //第一次时 配置此项让系统自动创建表
//		tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        super.applyPasswordAuthenticationConfig(http);

        http
            .apply(validateCodeSecurityConfig)
            .and()
            .apply(smsCodeAuthenticationSecurityConfig)
            .and()
            //将validateCodeFilter加在UsernamePasswordAuthenticationFilter之前
//			.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
            //身份认证：表单登录 任何请求都需要身份认证
            .addFilterBefore(tokenCheckFilter, AbstractPreAuthenticatedProcessingFilter.class)
            .rememberMe()
            .tokenRepository(persistentTokenRepository())
            .tokenValiditySeconds(securityProperties.getBcService().getRememberMeSeconds())
            .userDetailsService(userDetailsService)
//		http.httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers(BcServiceConstants.DEFAULT_UNAUTHENTICATION_URL,
                    BcServiceConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                    BcServiceConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                    "/auth/*", "/oauth/token").permitAll()
            .anyRequest()
            .authenticated()
            //暂时关闭csrf 跨站请求伪造防护功能
            .and().csrf().disable();

    }

    /**
     * 需要配置这个支持password模式 support password grant type
     *
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
