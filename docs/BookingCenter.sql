-- ----------------------------
-- Table structure for bc_user
-- ----------------------------
CREATE TABLE bc_user (
	`id` VARCHAR (32) NOT NULL COMMENT '主键id',
	`username` VARCHAR (64) NOT NULL COMMENT '用户名',
	`password` VARCHAR (64) NOT NULL COMMENT '密码 MD5(MD5(pass明文+固定salt) + salt)',
	`salt` VARCHAR (10) DEFAULT NULL COMMENT '盐',
	`nickname` VARCHAR (255) DEFAULT NULL COMMENT '昵称',
	`avatar_path` VARCHAR (128) NOT NULL COMMENT '头像',
	`mobile` VARCHAR (32) DEFAULT NULL COMMENT '手机号',
	`email` VARCHAR (32) DEFAULT NULL COMMENT '邮箱地址',
	`sex` INT (1) DEFAULT '2' COMMENT '性别 1:男  0:女  2:保密',
	`birthday` DATE DEFAULT NULL COMMENT '生日',
	`created_time` DATETIME NOT NULL COMMENT '创建时间',
	`updated_time` DATETIME DEFAULT NULL COMMENT '更新时间',
	`last_login_date` DATETIME DEFAULT NULL COMMENT '上次登录时间',
	`login_count` INT (11) DEFAULT '0' COMMENT '登录次数',
	PRIMARY KEY (id),
    UNIQUE KEY `bc_user_username` (`username`) USING BTREE,
    UNIQUE KEY `bc_user_email` (`email`) USING BTREE,
    UNIQUE KEY `bc_user_mobile` (`mobile`) USING BTREE
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '用户表 ';