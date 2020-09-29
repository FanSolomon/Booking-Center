package com.fansolomon.bookingService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fansolomon.bookingService.entity.OrderStatus;
import com.fansolomon.bookingService.mapper.OrderStatusMapper;
import com.fansolomon.bookingService.service.IOrderStatusService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单状态表 服务实现类
 * </p>
 *
 * @author zhangyuting
 * @since 2020-09-29
 */
@Service
public class OrderStatusServiceImpl extends ServiceImpl<OrderStatusMapper, OrderStatus> implements IOrderStatusService {

}
