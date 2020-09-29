package com.fansolomon.bookingService.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fansolomon.bookingService.entity.OrderTickets;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单券关联表 Mapper 接口
 * </p>
 *
 * @author zhangyuting
 * @since 2020-09-29
 */
@Mapper
public interface OrderTicketsMapper extends BaseMapper<OrderTickets> {

}
