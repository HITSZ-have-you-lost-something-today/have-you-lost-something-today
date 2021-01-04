package club.huangblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 建表SQL语句：
 * CREATE TABLE `feedback` (
 *   `feedback_id` bigint(255) unsigned NOT NULL AUTO_INCREMENT COMMENT '反馈事件的ID',
 *   `is_delete` tinyint(4) NOT NULL DEFAULT '0' COMMENT '默认为零，不能够被删除',
 *   `gmt_create` datetime NOT NULL COMMENT '反馈事件的上传时间',
 *   `content` varchar(255) NOT NULL COMMENT '反馈事件的内容',
 *   `email` varchar(255) NOT NULL COMMENT '反馈者的邮箱',
 *   PRIMARY KEY (`feedback_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 *
 * @author huang
 * @since 2021-01-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 反馈事件的ID
     */
    @TableId(value = "feedback_id", type = IdType.AUTO)
    private Long feedbackId;

    /**
     * 默认为零，不能够被删除
     */
    private Integer isDelete;

    /**
     * 反馈事件的上传时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 反馈事件的内容
     */
    private String content;

    /**
     * 反馈者的邮箱
     */
    private String email;


}
