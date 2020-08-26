package com.fansolomon.bookingService.config;

import com.alibaba.fastjson.JSONObject;
import com.fansolomon.bookingService.entity.dto.ResultDTO;
import com.fansolomon.bookingService.properties.ErrorConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义Token校验异常处理
 * {@link org.springframework.security.oauth2.provider.token.DefaultTokenServices}
 * 处理token异常 line 235
 * @author Colin
 * @since 2020-08-26
 */
@Slf4j
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String authExceptionMsg = authException.getMessage();
        String code = ErrorConstants.INVALID_ACCESS_TOKEN_CODE;
        String errorMsg = ErrorConstants.INVALID_ACCESS_TOKEN_MESSAGE;
        log.info("Token校验异常，authExceptionMessage:{}", authExceptionMsg);
        // token过期情况
        if (authExceptionMsg.contains("Access token expired:")) {
            code = ErrorConstants.ACCESS_TOKEN_EXPIRED_CODE;
            errorMsg = ErrorConstants.ACCESS_TOKEN_EXPIRED_MESSAGE;
        }
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().print(JSONObject.toJSONString(new ResultDTO<>(code, "", errorMsg)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
