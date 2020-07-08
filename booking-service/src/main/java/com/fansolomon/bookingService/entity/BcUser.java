package com.fansolomon.bookingService.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表 
 * </p>
 *
 * @author zhangyuting
 * @since 2020-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="BcUser对象", description="用户表 ")
public class BcUser implements Serializable {

    private static final long serialVersionUID = 31492452026783032L;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码 MD5(MD5(pass明文+固定salt) + salt)")
    private String password;

    @ApiModelProperty(value = "盐")
    private String salt;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatarPath;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "邮箱地址")
    private String email;

    @ApiModelProperty(value = "性别 1:男  0:女  2:保密")
    private Integer sex;

    @ApiModelProperty(value = "生日")
    private LocalDate birthday;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "上次登录时间")
    private LocalDateTime lastLoginDate;

    @ApiModelProperty(value = "登录次数")
    private Integer loginCount;


}
