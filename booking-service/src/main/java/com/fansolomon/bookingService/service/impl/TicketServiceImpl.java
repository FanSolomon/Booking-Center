package com.fansolomon.bookingService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fansolomon.bookingService.entity.Ticket;
import com.fansolomon.bookingService.mapper.TicketMapper;
import com.fansolomon.bookingService.service.ITicketService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 入场券表 服务实现类
 * </p>
 *
 * @author zhangyuting
 * @since 2020-09-29
 */
@Service
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket> implements ITicketService {

}
