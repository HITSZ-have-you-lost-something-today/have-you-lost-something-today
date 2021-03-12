package work.hitsz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import work.hitsz.entity.User;
import work.hitsz.mapper.UserMapper;
import work.hitsz.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Huang
 * @since 2021-02-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> getUserMessageByOtherMessage(User user) {
        return userMapper.selectList(user);
    }
}
