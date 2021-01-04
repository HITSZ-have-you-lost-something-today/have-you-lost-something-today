package club.huangblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 建表SQL语句:
 * CREATE TABLE `user` (
 *   `user_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自创建用户ID',
 *   `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '不能够被删除，默认为零',
 *   `gmt_create` datetime NOT NULL COMMENT '首次创建用户的时间',
 *   `wx_id` varchar(100) NOT NULL COMMENT '用户的微信ID',
 *   `wx_name` varchar(255) NOT NULL COMMENT '微信的昵称',
 *   `wx_avatar_url` varchar(255) NOT NULL COMMENT '微信头像的地址',
 *   `is_official` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否为学校官方失物招领处，默认不是，该属性由管理员手动修改',
 *   PRIMARY KEY (`user_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 *
 * @author huang
 * @since 2021-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自创建用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 不能够被删除，默认为零
     */
    private Integer isDelete;

    /**
     * 首次创建用户的时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 用户的微信ID
     */
    private String wxId;

    /**
     * 微信的昵称
     */
    private String wxName;

    /**
     * 微信头像的地址
     */
    private String wxAvatarUrl;

    /**
     * 是否为学校官方失物招领处，默认不是，该属性由管理员手动修改
     */
    private Integer isOfficial;


}
