package com.fansolomon.bookingService.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fansolomon.bookingService.entity.OrderStatus;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单状态表 Mapper 接口
 * </p>
 *
 * @author zhangyuting
 * @since 2020-09-29
 */
@Mapper
public interface OrderStatusMapper extends BaseMapper<OrderStatus> {

}
