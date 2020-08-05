package com.fansolomon.oauthServer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fansolomon.bookingCommon.entity.BcUser;
import com.fansolomon.bookingCommon.mapper.BcUserMapper;
import com.fansolomon.oauthServer.service.IBcUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表  服务实现类
 * </p>
 *
 * @author zhangyuting
 * @since 2020-07-08
 */
@Service
public class BcUserServiceImpl extends ServiceImpl<BcUserMapper, BcUser> implements IBcUserService {

}
