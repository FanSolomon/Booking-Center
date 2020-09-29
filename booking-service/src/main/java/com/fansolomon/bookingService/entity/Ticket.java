package com.fansolomon.bookingService.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 入场券表
 * </p>
 *
 * @author zhangyuting
 * @since 2020-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Ticket对象", description="入场券表")
public class Ticket implements Serializable {

    private static final long serialVersionUID = 5375813706856614557L;

    @TableId
    private String id;

    @ApiModelProperty(value = "票名称")
    private String ticketName;

    @ApiModelProperty(value = "分类外键id")
    private Integer catId;

    @ApiModelProperty(value = "一级分类外键id，用于优化查询")
    private Integer rootCatId;

    @ApiModelProperty(value = "累计销售")
    private Integer sellCounts;

    @ApiModelProperty(value = "上下架状态,0:上架 1:下架")
    private Integer status;

    @ApiModelProperty(value = "票内容")
    private String content;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "创建用户id")
    private String createdUser;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "更新用户id")
    private String updatedUser;


}
