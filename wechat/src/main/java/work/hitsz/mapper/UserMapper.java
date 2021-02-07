package work.hitsz.mapper;

import lombok.Data;
import org.springframework.stereotype.Component;
import work.hitsz.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Huang
 * @since 2021-02-02
 */
@Component
public interface UserMapper extends BaseMapper<User> {

}
