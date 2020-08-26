package com.fansolomon.bookingService.feign;

import com.fansolomon.bookingService.config.FeignClientConfig;
import com.fansolomon.bookingService.feign.fallback.OauthServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author zhangyuting
 * @since 2020-8-18
 */
@FeignClient(name = "oauth-server", fallback = OauthServiceFallback.class, configuration = FeignClientConfig.class)
public interface OauthService {

    @RequestMapping(value = "/oauth/token", method = RequestMethod.POST, headers = {"Content-Type: application/x-www-form-urlencoded", "Authorization=Basic b2F1dGgtc2VydmVyOm9hdXRoU2VjcmV0"})
    Map<String, String> getOauthToken(@RequestParam("grant_type") String grantType, @RequestParam("scope") String scope
            , @RequestParam("username") String username, @RequestParam("password") String password);

    @RequestMapping(value = "/oauth/token", method = RequestMethod.POST, headers = {"Content-Type: application/x-www-form-urlencoded", "Authorization=Basic b2F1dGgtc2VydmVyOm9hdXRoU2VjcmV0"})
    Map<String, String> refreshOauthToken(@RequestParam("grant_type") String grantType, @RequestParam("refresh_token") String refreshToken);
}
