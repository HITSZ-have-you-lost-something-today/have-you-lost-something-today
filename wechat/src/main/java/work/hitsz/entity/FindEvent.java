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
     * 首次创建该事件的时间
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
