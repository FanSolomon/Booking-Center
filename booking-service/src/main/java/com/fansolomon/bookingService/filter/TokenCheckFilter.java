package com.fansolomon.bookingService.filter;

import com.alibaba.fastjson.JSONObject;
import com.fansolomon.bookingService.entity.dto.ResultDTO;
import com.fansolomon.bookingService.properties.ErrorConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Colin
 * @since 2020-07-21
 * token检查过滤器
 */
@Slf4j
@Component
public class TokenCheckFilter extends OncePerRequestFilter implements InitializingBean {

    @Value("${fansolomon.security.outOfFilterUri}")
    private String outOfFilterUri;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String header = httpServletRequest.getHeader("Authorization");
        String requestUrl = httpServletRequest.getServletPath();
        log.info("requestUrl is:{}",requestUrl);

        if (StringUtils.isNotBlank(outOfFilterUri)) {
            String[] outOfFilterUriArr = outOfFilterUri.split(";");
            for (String uri : outOfFilterUriArr) {
                if (requestUrl.startsWith(uri)) {
                    log.info("requestUrl {} is outOfFilterUri", requestUrl);
                    chain.doFilter(request, response);
                    return;
                }
            }
        }

        String token = "";
        if (StringUtils.isNotBlank(header) && header.startsWith("Bearer ")) {
            token = header.replace("Bearer ", "");
        }
        if (StringUtils.isNotBlank(token)) {
            log.info("token exist, start to check token");
            Jwt tokenMessage = JwtHelper.decode(token);
            JSONObject tokenObject = JSONObject.parseObject(tokenMessage.getClaims());
            String userId = (String) tokenObject.get("user_id");
            // TODO 取redis进行比对
        } else {
            log.info("Bearer token not found");
            ResultDTO resultDTO = new ResultDTO(ErrorConstants.TOKEN_NOT_EXIST_CODE, ErrorConstants.TOKEN_NOT_EXIST_MESSAGE);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print(JSONObject.toJSONString(resultDTO));
            return;
        }

        chain.doFilter(request, response);
    }
}
