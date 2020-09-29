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
 * 入场券图片表 
 * </p>
 *
 * @author zhangyuting
 * @since 2020-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TicketImg对象", description="入场券图片表 ")
public class TicketImg implements Serializable {

    private static final long serialVersionUID = -8058234705173017009L;

    @TableId
    private String id;

    @ApiModelProperty(value = "票外键id")
    private String ticketId;

    @ApiModelProperty(value = "图片地址")
    private String imgPath;

    @ApiModelProperty(value = "顺序 从小到大")
    private Integer sort;

    @ApiModelProperty(value = "是否主图 1：是，0：否")
    private Integer isMain;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "创建用户id")
    private String createdUser;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "更新用户id")
    private String updatedUser;


}
