package com.fansolomon.bookingService.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 入场券规格表
 * </p>
 *
 * @author zhangyuting
 * @since 2020-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TicketSpec对象", description="入场券规格表")
public class TicketSpec implements Serializable {

    private static final long serialVersionUID = 6737185133792773468L;

    @TableId
    private String id;

    @ApiModelProperty(value = "券外键id")
    private String itemId;

    @ApiModelProperty(value = "规格名称")
    private String name;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "折扣力度")
    private BigDecimal discounts;

    @ApiModelProperty(value = "优惠价")
    private BigDecimal priceDiscount;

    @ApiModelProperty(value = "原价")
    private BigDecimal priceNormal;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "创建用户id")
    private String createdUser;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "更新用户id")
    private String updatedUser;


}
