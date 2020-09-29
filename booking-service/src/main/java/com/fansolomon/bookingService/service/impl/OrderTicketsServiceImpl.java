package com.fansolomon.bookingService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fansolomon.bookingService.entity.OrderTickets;
import com.fansolomon.bookingService.mapper.OrderTicketsMapper;
import com.fansolomon.bookingService.service.IOrderTicketsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单券关联表 服务实现类
 * </p>
 *
 * @author zhangyuting
 * @since 2020-09-29
 */
@Service
public class OrderTicketsServiceImpl extends ServiceImpl<OrderTicketsMapper, OrderTickets> implements IOrderTicketsService {

}
