package com.fansolomon.bookingService.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fansolomon.bookingCommon.entity.BcUser;
import com.fansolomon.bookingCommon.mapper.BcUserMapper;
import com.fansolomon.bookingCommon.utils.RedisUtil;
import com.fansolomon.bookingCommon.utils.SnowFlake;
import com.fansolomon.bookingService.entity.dto.ResultDTO;
import com.fansolomon.bookingService.entity.param.LoginParam;
import com.fansolomon.bookingService.feign.OauthService;
import com.fansolomon.bookingService.properties.BSRedisConstants;
import com.fansolomon.bookingService.properties.BcServiceConstants;
import com.fansolomon.bookingService.properties.ErrorConstants;
import com.fansolomon.bookingService.service.AuthService;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

import static com.baomidou.mybatisplus.extension.toolkit.SqlHelper.retBool;

/**
 * @author zhangyuting
 * @since 2020-7-22
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private BcUserMapper bcUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SnowFlake snowFlake;

    @Autowired
    private OauthService oauthService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public ResultDTO<Map<String, String>> login(LoginParam loginParam) {
        ResultDTO<Map<String, String>> resultDTO = new ResultDTO<>();
        String password = loginParam.getPassword();
        String username = loginParam.getUsername();
        String email = loginParam.getEmail();
        String mobile = loginParam.getMobile();

        // 检查用户是否存在
        BcUser bcUser = findUser(username, email, mobile);
        if (bcUser == null) {
            return new ResultDTO<>(ErrorConstants.USER_NOT_FOUND_CODE, ErrorConstants.USER_NOT_FOUND_MESSAGE);
        }

        Map<String, String> tokenInfo = null;
        try {
            tokenInfo = oauthService.getOauthToken("password", "all", bcUser.getUsername(), password);
        } catch (FeignException.BadRequest e) {
            log.info("获取tokenInfo失败，失败信息:{}", e.getMessage());
            return new ResultDTO<>(ErrorConstants.WRONG_USERNAME_OR_PASSWORD_CODE, ErrorConstants.WRONG_USERNAME_OR_PASSWORD_MESSAGE);
        }

        if (tokenInfo == null) {
            log.info("获取tokenInfo失败");
            return new ResultDTO<>(ErrorConstants.GET_OAUTH_TOKEN_ERROR_CODE, ErrorConstants.GET_OAUTH_TOKEN_ERROR_MESSAGE);
        }

        // 将refresh_token及部分access_token存入redis
        if (StringUtils.isNotBlank(tokenInfo.get("refresh_token")) && StringUtils.isNotBlank(tokenInfo.get("access_token"))) {
            redisUtil.set(BSRedisConstants.REFRESH_TOKEN + bcUser.getUsername(), tokenInfo.get("refresh_token"), BcServiceConstants.REFRESH_TOKEN_VALIDITY);
            redisUtil.set(BSRedisConstants.PART_ACCESS_TOKEN + bcUser.getUsername(), cutToken(tokenInfo.get("access_token")), BcServiceConstants.REFRESH_TOKEN_VALIDITY);
            log.info("token已存入redis");
        }

        tokenInfo.remove("refresh_token");
        tokenInfo.remove("jti");
        resultDTO.setData(tokenInfo);
        return resultDTO;
    }

    @Override
    @Transactional
    public ResultDTO register(LoginParam loginParam) {
        log.info("用户注册开始,Username:{}", loginParam.getUsername());
        boolean result = false;
        String password = loginParam.getPassword();
        BcUser bcUser = new BcUser();
        bcUser.setUsername(loginParam.getUsername());
        bcUser.setEmail(loginParam.getEmail());
        bcUser.setSex(2);
        bcUser.setCreatedTime(LocalDateTime.now());
        bcUser.setLoginCount(0);
        bcUser.setAvatarPath("default.jpg");
        try {
            String encodePassword = passwordEncoder.encode(password);
            String id = Long.toString(snowFlake.nextId());
            bcUser.setId(id);
            bcUser.setPassword(encodePassword);
            log.info("原密码:{},加密后:{}", password, encodePassword);
            result = retBool(bcUserMapper.insert(bcUser));
            log.info("用户注册完成,id:{}", id);
        } catch (Exception e) {
            log.info("用户注册出错{}", e.getMessage());
            return new ResultDTO<>(ErrorConstants.USER_REGISTER_ERROR_CODE, ErrorConstants.USER_REGISTER_ERROR_MESSAGE);
        }
        ResultDTO resultDTO = result ? new ResultDTO<>() : new ResultDTO<>(ErrorConstants.USER_REGISTER_ERROR_CODE,
                ErrorConstants.USER_REGISTER_ERROR_MESSAGE);
        return resultDTO;
    }

    @Override
    public ResultDTO<Map<String, String>> refreshToken(String token) {
        Jwt tokenMessage = JwtHelper.decode(token);
        JSONObject tokenObject = JSONObject.parseObject(tokenMessage.getClaims());
        String userName = (String) tokenObject.get("user_name");

        String partAccessToken = redisUtil.get(BSRedisConstants.PART_ACCESS_TOKEN + userName);
        String isCheckAccessToken = redisUtil.get(BSRedisConstants.IS_CHECK_ACCESS_TOKEN);
        log.info("isCheckAccessToken:{}", isCheckAccessToken);
        Map<String, String> tokenInfo = null;
        try {
            if (StringUtils.equals(partAccessToken, cutToken(token)) || "0".equals(isCheckAccessToken)) {
                log.info("accessToken校验通过");
                String refreshToken = redisUtil.get(BSRedisConstants.REFRESH_TOKEN + userName);
                tokenInfo = oauthService.refreshOauthToken("refresh_token", refreshToken);
            } else {
                log.info("access_token与redis中数据不同，比对失败");
                return new ResultDTO<>(ErrorConstants.ACCESS_TOKEN_ERROR_CODE, ErrorConstants.ACCESS_TOKEN_ERROR_MESSAGE);
            }
        } catch (FeignException.BadRequest e) {
            log.info("刷新tokenInfo失败，失败信息:{}", e.getMessage());
            return new ResultDTO<>(ErrorConstants.REFRESH_TOKEN_ERROR_CODE, ErrorConstants.REFRESH_TOKEN_ERROR_MESSAGE);
        }

        if (tokenInfo == null) {
            log.info("刷新tokenInfo失败");
            return new ResultDTO<>(ErrorConstants.REFRESH_TOKEN_ERROR_CODE, ErrorConstants.REFRESH_TOKEN_ERROR_MESSAGE);
        }

        // 更新redis中的token
        if (StringUtils.isNotBlank(tokenInfo.get("refresh_token")) && StringUtils.isNotBlank(tokenInfo.get("access_token"))) {
            redisUtil.set(BSRedisConstants.REFRESH_TOKEN + userName, tokenInfo.get("refresh_token"), BcServiceConstants.REFRESH_TOKEN_VALIDITY);
            redisUtil.set(BSRedisConstants.PART_ACCESS_TOKEN + userName, cutToken(tokenInfo.get("access_token")), BcServiceConstants.REFRESH_TOKEN_VALIDITY);
            log.info("token已存入redis");
        }

        tokenInfo.remove("refresh_token");
        tokenInfo.remove("jti");
        return new ResultDTO<>(tokenInfo);
    }

    /**
     * 根据用户名、邮箱或手机号查找用户
     * @param username
     * @param email
     * @param mobile
     * @return
     */
    private BcUser findUser(String username, String email, String mobile) {
        if (StringUtils.isAllBlank(username, email, mobile)) {
            return null;
        }
        LambdaQueryWrapper<BcUser> queryWrapper = new LambdaQueryWrapper<BcUser>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper = queryWrapper.eq(BcUser::getUsername, username);
        } else if (StringUtils.isNotBlank(email)) {
            queryWrapper = queryWrapper.eq(BcUser::getEmail, email);
        } else {
            queryWrapper = queryWrapper.eq(BcUser::getMobile, mobile);
        }
        return bcUserMapper.selectOne(queryWrapper);
    }

    /**
     * 切割token，只取一部分
     * @param token
     * @return
     */
    private String cutToken(String token) {
        if (token == null || token.length() < 20) {
            return null;
        }
        return token.substring(0,10) + token.substring(token.length() - 10);
    }
}
