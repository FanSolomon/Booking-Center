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
 * 订单表
 * </p>
 *
 * @author zhangyuting
 * @since 2020-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Order对象", description="订单表")
public class Order implements Serializable {

    private static final long serialVersionUID = -5009336080513403698L;

    @TableId
    private String id;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "订单总价格")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "实际支付总价格")
    private BigDecimal realPayAmount;

    @ApiModelProperty(value = "支付方式 0:余额支付 1:微信 2:支付宝")
    private Integer payMethod;

    @ApiModelProperty(value = "有效状态 0:有效，1:无效")
    private Integer status;

    @ApiModelProperty(value = "创建时间 （成交时间）")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;


}
