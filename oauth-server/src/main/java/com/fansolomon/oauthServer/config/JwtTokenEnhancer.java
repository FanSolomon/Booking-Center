package com.fansolomon.oauthServer.config;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author colin
 * @since 2020-08-14
 */
public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        // 设置token的自定义属性
        Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("creatTime", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
