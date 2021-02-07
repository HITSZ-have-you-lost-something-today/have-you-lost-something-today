package work.hitsz.utils;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * json格式模板类
 * @author Huang
 * @since 2021-2-2
 */
// POST请求
public class RestTemplateUtil {
   public static JSONObject doPost(String url, MultiValueMap<String, Object> param) {
       RestTemplate restTemplate = new RestTemplate();
       String s = restTemplate.postForObject(url, param, String.class);
       return JSONObject.parseObject(s);
   }
}
