package com.fansolomon.bookingService.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 订单券关联表
 * </p>
 *
 * @author zhangyuting
 * @since 2020-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="OrderTickets对象", description="订单券关联表")
public class OrderTickets implements Serializable {

    private static final long serialVersionUID = -1851870256366062607L;

    @ApiModelProperty(value = "归属订单id")
    private String orderId;

    @ApiModelProperty(value = "券id")
    private String ticketId;

    @ApiModelProperty(value = "券图片")
    private String ticketImg;

    @ApiModelProperty(value = "券名称")
    private String ticketName;

    @ApiModelProperty(value = "规格id")
    private String ticketSpecId;

    @ApiModelProperty(value = "规格名称")
    private String ticketSpecName;

    @ApiModelProperty(value = "成交价格")
    private BigDecimal price;

    @ApiModelProperty(value = "购买数量")
    private Integer buyCounts;


}
