package work.hitsz.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import work.hitsz.entity.User;
import work.hitsz.mapper.UserMapper;
import work.hitsz.utils.*;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * 用户登录controller
 * @author Huang
 * @since 2021-02-02
 */
@Api(value = "小程序用户相关接口")
@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;


    // 登录功能
    @ApiOperation(value = "小程序用户登录接口")
    @PostMapping(path = "/doLogin")
    @ResponseBody
    public JSONObject doLogin(@RequestParam(value = "code", required = false) String code,
                           @RequestParam(value = "rawData", required = false) String rawData,
                           @RequestParam(value = "signature", required = false) String signature,
                           @RequestParam(value = "encrypteData", required = false) String encrypteData,
                           @RequestParam(value = "iv", required = false) String iv) {

        // 用户非敏感信息 rawData
        // 签名 signature

        JSONObject rawDataJson = JSON.parseObject(rawData);
        // 1.接收小程序发送的code
        // 2.开发者服务器 登录凭证校验接口 appid + appsecret + code
        JSONObject SessionKeyOpenid = WeChatUtil.getSessionKeyOrOpenId(code);
        // 3.接受微信接口服务 获取返回的参数
        String openid = SessionKeyOpenid.getString("openid");
        String sessionKey = SessionKeyOpenid.getString("session_key");

        // 4.校验签名 小程序发送的签名signature与服务器端生成的签名signature2 = sha1(rawData + sessionKey)
        String signature2 = DigestUtils.sha1Hex(rawData + sessionKey);
        if (!signature.equals(signature2)) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", 500);
            jsonObject.put("msg", "签名校验失败");
            return jsonObject;
        }
        // 5.根据返回的User实体类, 判断用户是否为新用户，如果是的话，保存到数据库中，否则，更新数据
        QueryWrapper<User> userWrapper = new QueryWrapper<User>();
        userWrapper.ge("wxId", openid);
        User user = this.userMapper.selectOne(userWrapper);
        // uuid生成唯一key，用于维护用户与服务端的会话
        String skey = UUID.randomUUID().toString();
        if (user == null) {
            // 用户信息入库

            // 微信昵称
            String nickName = rawDataJson.getString("nickName");
            // 头像地址
            String avatarUrl = rawDataJson.getString("avatarUrl");
            // 性别 （用不上
            String gender = rawDataJson.getString("gender");
            // 城市（用不上
            String city = rawDataJson.getString("city");
            // 国家 （用不上
            String country = rawDataJson.getString("country");
            // 省份 （用不上
            String province = rawDataJson.getString("province");
            // 新增用户到数据库中
            User aUser = new User();
            LocalDateTime nowTime = LocalDateTime.now();
            aUser.setGmtCreate(nowTime);
            aUser.setWxId(openid);
            aUser.setIsDelete(0);
            aUser.setWxName(nickName);
            aUser.setWxAvatarUrl(avatarUrl);
            this.userMapper.insert(aUser);
        } else {
            // 已经存在，不做修改
        }
        // 不想弄会话，所以直接将自己的userId作为标识
        QueryWrapper<User> selectUserId = new QueryWrapper<>();
        selectUserId.ge("wxId", openid);
        User userId = this.userMapper.selectOne(selectUserId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 200);
        jsonObject.put("userId", userId.getUserId());
        jsonObject.put("data", userId);
        jsonObject.put("msg", "userId是唯一会话标识,相当于sessionKey");
        return jsonObject;
    }
}
