package com.fansolomon.bookingService.feign.fallback;

import com.fansolomon.bookingService.feign.OauthService;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Oauth服务降级实现类
 *
 * @author zhangyuting
 * @since 2020-8-18
 */
@Component
public class OauthServiceFallback implements OauthService {

    @Override
    public Map<String, String> getOauthToken(String grantType, String scope, String username, String password) {
        return null;
    }
}
