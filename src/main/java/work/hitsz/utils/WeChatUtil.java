package work.hitsz.utils;

import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static work.hitsz.config.UserConstantInterface.*;
import static work.hitsz.config.UserConstantInterface.WX_LOGIN_GRANT_TYPE;

/**
 * @author Huang
 * @since 2021-2-4
 */
public class WeChatUtil {
    public static JSONObject getSessionKeyOrOpenId(String code) {
        // code是通过wx.login接口获得的临时登录凭证
        String url = WX_LOGIN_URL;
        Map<String, String> param = new HashMap<String, String>();
        param.put("appid", WX_LOGIN_APPID);
        param.put("secret", WX_LOGIN_SECRET);
        param.put("js_code", code);
        param.put("grant_type", WX_LOGIN_GRANT_TYPE);

        String result = HttpClientUtil.doGet(url, param);
        JSONObject jsonObject = JSON.parseObject(HttpClientUtil.doPost(url, param));
        return jsonObject;
    }

    public static JSONObject getUserInfo(String encryptedData, String sessionKey, String iv) throws Exception {
        // 被加密的数据
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decode(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSON.parseObject(result);
            }
        } catch (Exception e) {
        }
        return null;
    }
}
