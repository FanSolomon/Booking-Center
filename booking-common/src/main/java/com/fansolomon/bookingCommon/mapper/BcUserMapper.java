package com.fansolomon.bookingCommon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fansolomon.bookingCommon.entity.BcUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表  Mapper 接口
 * </p>
 *
 * @author zhangyuting
 * @since 2020-07-08
 */
@Mapper
public interface BcUserMapper extends BaseMapper<BcUser> {

}
