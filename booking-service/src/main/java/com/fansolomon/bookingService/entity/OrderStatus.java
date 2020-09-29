package com.fansolomon.bookingService.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单状态表
 * </p>
 *
 * @author zhangyuting
 * @since 2020-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="OrderStatus对象", description="订单状态表")
public class OrderStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单ID 对应订单表的主键id")
    private String orderId;

    @ApiModelProperty(value = "订单状态")
    private Integer orderStatus;

    @ApiModelProperty(value = "订单创建时间 对应[10:待付款]状态")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "支付成功时间 对应[20:已付款，待发货]状态")
    private LocalDateTime payTime;

    @ApiModelProperty(value = "交易成功时间 对应[30：交易成功]状态")
    private LocalDateTime successTime;

    @ApiModelProperty(value = "交易关闭时间 对应[40：交易关闭]状态")
    private LocalDateTime closeTime;


}
