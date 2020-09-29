package com.fansolomon.bookingService.authController;

import com.fansolomon.bookingService.entity.dto.ResultDTO;
import com.fansolomon.bookingService.entity.param.LoginParam;
import com.fansolomon.bookingService.properties.ErrorConstants;
import com.fansolomon.bookingService.service.AuthService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author zhangyuting
 * @since 2020-7-22
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @ApiOperation("登录")
    @RequestMapping("/login")
    public ResultDTO<Map<String, String>> login(@RequestBody LoginParam loginParam) {
        if (StringUtils.isBlank(loginParam.getPassword()) || StringUtils.isAllBlank(loginParam.getUsername()
                , loginParam.getMobile(), loginParam.getEmail())) {
            return new ResultDTO<>(ErrorConstants.LOGIN_PARAM_ERROR_CODE, ErrorConstants.LOGIN_PARAM_ERROR_MESSAGE);
        }
        return authService.login(loginParam);
    }

    @ApiOperation("注册")
//    @RequestMapping("/register")
    public ResultDTO register(@RequestBody @Validated LoginParam loginParam) {
        return authService.register(loginParam);
    }

    @ApiOperation("刷新token")
    @RequestMapping("/refreshToken")
    public ResultDTO<Map<String, String>> refreshToken(@RequestParam("token") String token) {
        return authService.refreshToken(token);
    }
}
