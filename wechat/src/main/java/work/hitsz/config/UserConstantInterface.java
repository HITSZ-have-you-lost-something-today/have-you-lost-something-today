package work.hitsz.config;

public interface UserConstantInterface {
    // 请求的api,微信官方提供
    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";

    // 微信小程序唯一标识 appid, 可以看作是账号
    public static final String WX_LOGIN_APPID = "wxec299e55ef347b13";

    // 微信小程序对应的密钥, 可以看作是密码
    public static final String WX_LOGIN_SECRET = "48365f013f6de82957e07a39b003717e";

    // 固定参数
    public static final String WX_LOGIN_GRANT_TYPE = "authorization_code";

}