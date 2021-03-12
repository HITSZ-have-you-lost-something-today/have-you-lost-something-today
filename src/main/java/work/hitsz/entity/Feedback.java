package work.hitsz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Huang
 * @since 2021-02-02
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
