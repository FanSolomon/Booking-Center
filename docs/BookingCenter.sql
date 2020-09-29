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

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(48) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ticket
-- ----------------------------
CREATE TABLE `ticket` (
  `id` varchar(32) NOT NULL COMMENT '票主键id',
  `ticket_name` varchar(128) NOT NULL COMMENT '票名称',
  `cat_id` int(5) DEFAULT NULL COMMENT '分类外键id',
  `root_cat_id` int(5) DEFAULT NULL COMMENT '一级分类外键id，用于优化查询',
  `sell_counts` int(11) NOT NULL COMMENT '累计销售',
  `status` int(1) NOT NULL COMMENT '上下架状态,0:上架 1:下架',
  `content` varchar(2048) DEFAULT NULL COMMENT '票内容',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `created_user` varchar(32) NOT NULL COMMENT '创建用户id',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_user` varchar(32) DEFAULT NULL COMMENT '更新用户id',
  PRIMARY KEY (`id`),
  KEY `ticket_name_index` (`ticket_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='入场券表';

-- ----------------------------
-- Table structure for category
-- ----------------------------
CREATE TABLE `category` (
  `id` int(5) NOT NULL AUTO_INCREMENT COMMENT '分类id主键',
  `name` varchar(32) NOT NULL COMMENT '分类名称',
  `type` int(1) NOT NULL COMMENT '分类类型 1:一级大分类2:二级分类3:三级小分类',
  `father_id` int(5) NOT NULL COMMENT '父id 1级分类则为0，二级三级分别依赖上一级',
  `detail` varchar(128) DEFAULT NULL COMMENT '描述',
  `cat_image` varchar(64) DEFAULT NULL COMMENT '分类图',
  PRIMARY KEY (`id`),
  KEY `father_id_index` (`father_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='券分类信息表';

-- ----------------------------
-- Table structure for ticket_img
-- ----------------------------
CREATE TABLE `ticket_img` (
  `id` varchar(32) NOT NULL COMMENT '图片主键',
  `ticket_id` varchar(32) NOT NULL COMMENT '票外键id',
  `img_path` varchar(128) NOT NULL COMMENT '图片地址',
  `sort` int(5) NOT NULL COMMENT '顺序 从小到大',
  `is_main` int(1) NOT NULL COMMENT '是否主图 1：是，0：否',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `created_user` varchar(32) NOT NULL COMMENT '创建用户id',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_user` varchar(32) DEFAULT NULL COMMENT '更新用户id',
  PRIMARY KEY (`id`),
  KEY `ticket_id_index` (`ticket_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='入场券图片表 ';

-- ----------------------------
-- Table structure for ticket_spec
-- ----------------------------
CREATE TABLE `ticket_spec` (
  `id` varchar(32) NOT NULL COMMENT '券规格id',
  `item_id` varchar(32) NOT NULL COMMENT '券外键id',
  `name` varchar(32) NOT NULL COMMENT '规格名称',
  `stock` int(11) NOT NULL COMMENT '库存',
  `discounts` decimal(4,2) NOT NULL COMMENT '折扣力度',
  `price_discount` decimal(18,2) NOT NULL COMMENT '优惠价',
  `price_normal` decimal(18,2) NOT NULL COMMENT '原价',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `created_user` varchar(32) NOT NULL COMMENT '创建用户id',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_user` varchar(32) DEFAULT NULL COMMENT '更新用户id',
  PRIMARY KEY (`id`),
  KEY `item_id_index` (`item_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='入场券规格表';

-- ----------------------------
-- Table structure for order
-- ----------------------------
CREATE TABLE `order` (
  `id` varchar(64) NOT NULL COMMENT '订单编号',
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `total_amount` decimal(18,2) NOT NULL COMMENT '订单总价格',
  `real_pay_amount` decimal(18,2) NOT NULL COMMENT '实际支付总价格',
  `pay_method` int(1) NOT NULL COMMENT '支付方式 0:余额支付 1:微信 2:支付宝',
  `status` int(1) NOT NULL COMMENT '有效状态 0:有效，1:无效',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间 （成交时间）',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `user_id_index` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';

-- ----------------------------
-- Table structure for order_tickets
-- ----------------------------
CREATE TABLE `order_tickets` (
  `order_id` varchar(64) NOT NULL COMMENT '归属订单id',
  `ticket_id` varchar(32) NOT NULL COMMENT '券id',
  `ticket_img` varchar(128) NOT NULL COMMENT '券图片',
  `ticket_name` varchar(128) NOT NULL COMMENT '券名称',
  `ticket_spec_id` varchar(32) NOT NULL COMMENT '规格id',
  `ticket_spec_name` varchar(32) NOT NULL COMMENT '规格名称',
  `price` decimal(18,2) NOT NULL COMMENT '成交价格',
  `buy_counts` int(11) NOT NULL COMMENT '购买数量',
  PRIMARY KEY (`order_id`,`ticket_id`,`ticket_spec_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单券关联表';

-- ----------------------------
-- Table structure for order_status
-- ----------------------------
CREATE TABLE `order_status` (
  `order_id` varchar(64) NOT NULL COMMENT '订单ID 对应订单表的主键id',
  `order_status` int(11) NOT NULL COMMENT '订单状态',
  `created_time` datetime DEFAULT NULL COMMENT '订单创建时间 对应[10:待付款]状态',
  `pay_time` datetime DEFAULT NULL COMMENT '支付成功时间 对应[20:已付款，待发货]状态',
  `success_time` datetime DEFAULT NULL COMMENT '交易成功时间 对应[30：交易成功]状态',
  `close_time` datetime DEFAULT NULL COMMENT '交易关闭时间 对应[40：交易关闭]状态',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单状态表';
