package work.hitsz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
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
public class User extends Wrapper<User> implements Serializable {

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


    @Override
    public User getEntity() {
        return null;
    }

    @Override
    public MergeSegments getExpression() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public String getSqlSegment() {
        return null;
    }
}
