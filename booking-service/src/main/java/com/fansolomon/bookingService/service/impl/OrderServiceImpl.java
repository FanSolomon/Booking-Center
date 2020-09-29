package com.fansolomon.bookingService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fansolomon.bookingService.entity.Order;
import com.fansolomon.bookingService.mapper.OrderMapper;
import com.fansolomon.bookingService.service.IOrderService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author zhangyuting
 * @since 2020-09-29
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
