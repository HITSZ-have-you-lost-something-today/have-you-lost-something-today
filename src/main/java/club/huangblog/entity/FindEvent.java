package club.huangblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 建表SQL语句:
 * CREATE TABLE `find_event` (
 *   `event_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '上传事件ID',
 *   `is_delete` tinyint(4) NOT NULL COMMENT '可以被删除，默认为1',
 *   `gmt_create` datetime NOT NULL COMMENT '首次创建该事件的事件',
 *   `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最近一次修改时间',
 *   `gmt_event` datetime NOT NULL COMMENT '捡到物品的时间，由用户上传',
 *   `thing_photo_url` varchar(255) NOT NULL COMMENT '上传的图片地址',
 *   `thing_type` varchar(50) NOT NULL COMMENT '上传的物品类型',
 *   `thing_where` varchar(255) NOT NULL COMMENT '上传的捡到东西的地点',
 *   `thing_info` varchar(255) NOT NULL COMMENT '用户上传的额外信息说明，包括联系方式等',
 *   `user_id` bigint(20) unsigned NOT NULL COMMENT '上传该事件用户的user表自创建id',
 *   PRIMARY KEY (`event_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 *
 * @author huang
 * @since 2021-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FindEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 上传事件ID
     */
    @TableId(value = "event_id", type = IdType.AUTO)
    private Long eventId;

    /**
     * 可以被删除，默认为1
     */
    private Integer isDelete;

    /**
     * 首次创建该事件的事件
     */
    private LocalDateTime gmtCreate;

    /**
     * 最近一次修改时间
     */
    private LocalDateTime gmtModified;

    /**
     * 捡到物品的时间，由用户上传
     */
    private LocalDateTime gmtEvent;

    /**
     * 上传的图片地址
     */
    private String thingPhotoUrl;

    /**
     * 上传的物品类型
     */
    private String thingType;

    /**
     * 上传的捡到东西的地点
     */
    private String thingWhere;

    /**
     * 用户上传的额外信息说明，包括联系方式等
     */
    private String thingInfo;

    /**
     * 上传该事件用户的user表自创建id
     */
    private Long userId;


}
