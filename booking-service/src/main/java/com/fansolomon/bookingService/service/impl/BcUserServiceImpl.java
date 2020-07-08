package com.fansolomon.bookingService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fansolomon.bookingService.entity.BcUser;
import com.fansolomon.bookingService.mapper.BcUserMapper;
import com.fansolomon.bookingService.service.IBcUserService;
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
