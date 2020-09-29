package com.fansolomon.bookingService.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fansolomon.bookingService.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 券分类信息表 Mapper 接口
 * </p>
 *
 * @author zhangyuting
 * @since 2020-09-29
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
