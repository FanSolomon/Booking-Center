package com.fansolomon.bookingService.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 券分类信息表
 * </p>
 *
 * @author zhangyuting
 * @since 2020-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Category对象", description="券分类信息表")
public class Category implements Serializable {

    private static final long serialVersionUID = 2129889796265221309L;

    @TableId
    private String id;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "分类类型 1:一级大分类2:二级分类3:三级小分类")
    private Integer type;

    @ApiModelProperty(value = "父id 1级分类则为0，二级三级分别依赖上一级")
    private Integer fatherId;

    @ApiModelProperty(value = "描述")
    private String detail;

    @ApiModelProperty(value = "分类图")
    private String catImage;


}
