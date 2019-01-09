
-- 表 order 结构
CREATE TABLE IF NOT EXISTS `order` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='订单';

DELETE FROM `order`;

INSERT INTO `order` (`id`, `user_id`, `name`) VALUES
	(1, 1, '订单一'),
	(2, 1, '订单二'),
	(3, 1, '订单三'),
	(4, 2, '订单四'),
	(5, 2, '订单五');


-- 表 order_product 结构
CREATE TABLE IF NOT EXISTS `order_product` (
  `order_id` int(10) unsigned NOT NULL,
  `product_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`order_id`,`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单产品关联表';

DELETE FROM `order_product`;

INSERT INTO `order_product` (`order_id`, `product_id`) VALUES
	(1, 1),
	(1, 2),
	(1, 3),
	(2, 2),
	(2, 3);


-- 表 product 结构
CREATE TABLE IF NOT EXISTS `product` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `price` double(10,2) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='产品';

DELETE FROM `product`;

INSERT INTO `product` (`id`, `name`, `price`) VALUES
	(1, '大白菜', 1.23),
	(2, '土豆', 2.45),
	(3, '西红柿', 3.78),
	(4, '芹菜', 2.08),
	(5, '鸡蛋', 0.51);

-- 表 role 结构
CREATE TABLE IF NOT EXISTS `role` (
  `id` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='角色';

DELETE FROM `role`;

INSERT INTO `role` (`id`, `name`) VALUES
	(1, '管理员'),
	(2, '总编'),
	(3, '编辑');

-- 表 user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` tinyint(3) unsigned NOT NULL COMMENT '角色ID',
  `username` varchar(32) NOT NULL COMMENT 'Email用户名，唯一',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `salt` varchar(6) NOT NULL COMMENT '盐',
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '姓名',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `sex` enum('male','female') DEFAULT NULL COMMENT '性别',
  `access` datetime DEFAULT NULL COMMENT 'datetime类型',
  `access_time` time DEFAULT NULL COMMENT 'time类型',
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `state` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 COMMENT='用户';

DELETE FROM `user`;

INSERT INTO `user` (`id`, `role_id`, `username`, `password`, `salt`, `name`, `birthday`, `sex`, `access`, `access_time`, `created`, `updated`, `state`) VALUES
	(1, 2, 'a@a.com', '6f5275a26b06ee3bdf4fb1d2ca89577c', 'wnyk0a', '雷小军', '1970-08-08', 'male', '1987-09-08 01:02:03', '01:02:03', '2017-07-01 09:36:23', '2018-05-29 19:05:19', 1),
	(2, 1, 'b@b.com', '3206c77753829fc30fffa37881a63341', 'xxmao1', '中国', '1960-05-05', 'female', '1987-09-08 01:02:03', '01:02:03', '2017-07-01 09:36:50', '2018-05-29 19:05:21', 1),
	(44, 1, 'asdf@dd.dd', '33f7fd7e7b6b10cbdbb8f3566794093d', '1guqtf', '张三', '1987-08-16', 'male', '2018-01-16 01:02:03', '01:02:03', '2018-05-30 16:57:24', '2018-05-30 16:57:24', 1),
	(48, 1, '111f@dd.dd', '15271e9a01417d5cc0baa6593f31c49b', 'je58s5', '张三', '1987-08-16', 'male', '2018-01-16 01:02:03', '01:02:03', '2018-05-30 17:54:56', '2018-05-30 17:54:56', 1),
	(49, 1, '5235235f@dd.dd', 'ffa5805341028bf2c710e687daeb5cf3', 'hbuibe', '张三', '1987-08-16', 'male', '2018-01-16 01:02:03', '01:02:03', '2018-05-30 17:55:58', '2018-05-30 17:55:58', 1),
	(50, 1, 'fdfdf5235f@dd.dd', 'bd7627c0b86ee7ad702c1ed6b9b8ea2d', 'd7jwws', '张三', '1987-08-16', 'male', '2018-01-16 01:02:03', '01:02:03', '2018-05-30 17:56:20', '2018-05-30 17:56:20', 1),
	(51, 1, 'vcvdf5235f@dd.dd', '3579c402def05272f5a6efd79eee9ccc', 'gbfnpu', '张三', '1987-08-16', 'male', '2018-01-16 01:02:03', '01:02:03', '2018-05-30 17:58:25', '2018-05-30 17:58:25', 1),
	(52, 1, '11vdf5235f@dd.dd', '8e1aa8b5614244d715cbc98c0bba2491', 'rxkop5', '张三', '1987-08-16', 'male', '2018-01-16 01:02:03', '01:02:03', '2018-05-30 17:58:40', '2018-05-30 17:58:40', 1),
	(53, 1, '11vdf5235f@cc.tt', '50f02765eb98aec634edeb99fca42798', 'dmghyi', '张三', '1987-08-16', 'male', '2018-01-16 01:02:03', '01:02:03', '2018-05-30 17:59:42', '2018-05-30 17:59:42', 1);


-- 表 user_detail 结构
CREATE TABLE IF NOT EXISTS `user_detail` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `address` varchar(32) NOT NULL DEFAULT '' COMMENT '地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='用户详情';

DELETE FROM `user_detail`;

INSERT INTO `user_detail` (`id`, `user_id`, `address`) VALUES
	(1, 2, '北京'),
	(2, 1, '广州'),
	(4, 44, '上海'),
	(5, 48, '深圳'),
	(6, 49, '厦门'),
	(7, 50, '纽约'),
	(8, 51, ''),
	(9, 52, ''),
	(10, 53, '');


-- 表 user_profile 结构
CREATE TABLE IF NOT EXISTS `user_profile` (
  `user_id` int(10) unsigned NOT NULL,
  `job` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  UNIQUE KEY `id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


DELETE FROM `user_profile`;

INSERT INTO `user_profile` (`user_id`, `job`) VALUES
	(1, '程序员'),
	(2, '工程师'),
	(44, '卧底'),
	(48, '演员'),
	(49, '歌手'),
	(50, '黑帮'),
	(51, ''),
	(52, ''),
	(53, '');
