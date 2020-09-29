package com.fansolomon.bookingService.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fansolomon.bookingService.entity.Ticket;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 入场券表 Mapper 接口
 * </p>
 *
 * @author zhangyuting
 * @since 2020-09-29
 */
@Mapper
public interface TicketMapper extends BaseMapper<Ticket> {

}
