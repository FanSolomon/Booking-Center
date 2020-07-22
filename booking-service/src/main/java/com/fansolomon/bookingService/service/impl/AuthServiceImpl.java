package com.fansolomon.bookingService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
import com.fansolomon.bookingService.entity.BcUser;
import com.fansolomon.bookingService.entity.dto.ResultDTO;
import com.fansolomon.bookingService.entity.param.LoginParam;
import com.fansolomon.bookingService.mapper.BcUserMapper;
import com.fansolomon.bookingService.properties.ErrorConstants;
import com.fansolomon.bookingService.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private BcUserMapper bcUserMapper;

    @Override
    public ResultDTO<Map<String, String>> login(LoginParam loginParam) {
        ResultDTO<Map<String, String>> resultDTO;
        String password = loginParam.getPassword();
        String username = loginParam.getUsername();
        String email = loginParam.getEmail();
        String mobile = loginParam.getMobile();

        // 检查用户是否存在
        BcUser bcUser = findUser(username, email, mobile);
        if (bcUser == null) {
            return new ResultDTO<>(ErrorConstants.USER_NOT_FOUND_CODE, ErrorConstants.USER_NOT_FOUND_MESSAGE);
        }

        // 校验密码


        // TODO 获取token，token放redis
        return null;
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
