package com.fansolomon.bookingService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fansolomon.bookingService.entity.TicketSpec;
import com.fansolomon.bookingService.mapper.TicketSpecMapper;
import com.fansolomon.bookingService.service.ITicketSpecService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 入场券规格表 服务实现类
 * </p>
 *
 * @author zhangyuting
 * @since 2020-09-29
 */
@Service
public class TicketSpecServiceImpl extends ServiceImpl<TicketSpecMapper, TicketSpec> implements ITicketSpecService {

}
