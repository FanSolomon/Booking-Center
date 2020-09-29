package com.fansolomon.bookingService.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fansolomon.bookingService.entity.Category;
import com.fansolomon.bookingService.mapper.CategoryMapper;
import com.fansolomon.bookingService.service.ICategoryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 券分类信息表 服务实现类
 * </p>
 *
 * @author zhangyuting
 * @since 2020-09-29
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
