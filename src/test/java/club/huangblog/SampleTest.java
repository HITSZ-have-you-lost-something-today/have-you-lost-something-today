package club.huangblog;

import club.huangblog.entity.User;
import club.huangblog.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

// 测试一小段代码，出现了错误不要慌，只要好好看log日志，就可以找出来

/**
 * 测试开发环境能否使用
 * @author HuangFeiyu
 * @date 2020.1.4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println("------ selectAll method test -------");
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

}
