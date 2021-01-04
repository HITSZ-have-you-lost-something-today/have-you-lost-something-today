package club.huangblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// TODO: 外键没有加入成功，可能需要全部删除重新再来一遍
// TODO: 封装网络层，实现登录操作
// TODO: 实现JSON格式数据的传递
@SpringBootApplication
@MapperScan("club.huangblog.*.mapper")
public class WechatApplication {

    public static void main(String[] args) {
        SpringApplication.run(WechatApplication.class, args);
    }

}