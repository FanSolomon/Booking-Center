package com.fansolomon.bookingService.controller;

import com.fansolomon.bookingService.entity.dto.ResultDTO;
import com.fansolomon.bookingService.properties.ErrorConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhangyuting
 * @since 2020-07-12
 */
@Slf4j
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    //spring security会将当前的请求缓存到session中（HttpSessionRequestCache）
    private RequestCache requestCache = new HttpSessionRequestCache();

    /**
     * 当需要身份认证时，跳转到这里
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping("/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ResultDTO requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (null != savedRequest) {
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("当前的请求：{}", targetUrl);
        }
        return new ResultDTO(ErrorConstants.AUTHENTICATION_FAILURE_CODE, ErrorConstants.AUTHENTICATION_FAILURE_MESSAGE);
        // TODO 前端待进行统一错误捕获并跳转
    }

}
