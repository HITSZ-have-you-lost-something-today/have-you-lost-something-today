/**
  * 数据库
  * @author Huang
 */
 CREATE DATABASE `wechat`;

/**
  * user信息表
  * @author Huang
 */
 DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自创建用户ID',
                        `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '不能够被删除，默认为零',
                        `gmt_create` datetime NOT NULL COMMENT '首次创建用户的时间',
                        `wx_id` varchar(100) NOT NULL COMMENT '用户的微信ID',
                        `wx_name` varchar(255) NOT NULL COMMENT '微信的昵称',
                        `wx_avatar_url` varchar(255) NOT NULL COMMENT '微信头像的地址',
                        `is_official` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否为学校官方失物招领处，默认不是，该属性由管理员手动修改',
                        PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/**
  * 上传事件表
  * @author Huang
 */
DROP TABLE IF EXISTS `find_event`;
CREATE TABLE `find_event` (
                              `event_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '上传事件ID',
                              `is_delete` tinyint(4) NOT NULL COMMENT '可以被删除，默认为1',
                              `gmt_create` datetime NOT NULL COMMENT '首次创建该事件的事件',
                              `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次修改时间',
                              `gmt_event` datetime NOT NULL COMMENT '捡到物品的时间，由用户上传',
                              `thing_photo_url` varchar(255) NOT NULL COMMENT '上传的图片地址',
                              `thing_type` varchar(50) NOT NULL COMMENT '上传的物品类型',
                              `thing_where` varchar(255) NOT NULL COMMENT '上传的捡到东西的地点',
                              `thing_info` varchar(255) NOT NULL COMMENT '用户上传的额外信息说明，包括联系方式等',
                              `user_id` bigint(20) unsigned NOT NULL COMMENT '上传该事件用户的user表自创建id',
                              PRIMARY KEY (`event_id`),
                              KEY `pk_find_user` (`user_id`) USING BTREE COMMENT '外键创建需要的索引',
                              CONSTRAINT `pk_find_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/**
  * 反馈信息表
  * @author Huang
 */
 DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
                            `feedback_id` bigint(255) unsigned NOT NULL AUTO_INCREMENT COMMENT '反馈事件的ID',
                            `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '默认为零，不能够被删除',
                            `gmt_create` datetime NOT NULL COMMENT '反馈事件的上传时间',
                            `content` varchar(255) NOT NULL COMMENT '反馈事件的内容',
                            `email` varchar(255) NOT NULL COMMENT '反馈者的邮箱',
                            PRIMARY KEY (`feedback_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
