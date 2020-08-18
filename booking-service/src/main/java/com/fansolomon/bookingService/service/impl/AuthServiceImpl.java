package com.fansolomon.bookingService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fansolomon.bookingCommon.entity.BcUser;
import com.fansolomon.bookingCommon.mapper.BcUserMapper;
import com.fansolomon.bookingCommon.utils.RedisUtil;
import com.fansolomon.bookingCommon.utils.SnowFlake;
import com.fansolomon.bookingService.entity.dto.ResultDTO;
import com.fansolomon.bookingService.entity.param.LoginParam;
import com.fansolomon.bookingService.feign.OauthService;
import com.fansolomon.bookingService.properties.BcServiceConstants;
import com.fansolomon.bookingService.properties.ErrorConstants;
import com.fansolomon.bookingService.service.AuthService;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

        // 将refresh_token存入redis
        if (StringUtils.isNotBlank(tokenInfo.get("refresh_token"))) {
            redisUtil.set("token:" + bcUser.getUsername(), tokenInfo.get("refresh_token"), BcServiceConstants.REFRESH_TOKEN_VALIDITY);
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
}
