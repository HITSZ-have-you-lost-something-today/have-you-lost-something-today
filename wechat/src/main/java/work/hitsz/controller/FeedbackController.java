package work.hitsz.controller;


import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

import work.hitsz.entity.Feedback;
import work.hitsz.service.FeedbackService;
import work.hitsz.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Huang
 * @since 2021-02-02
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Resource
    FeedbackService feedbackService;
    @Resource
    UserService userService;

    @GetMapping("/all")
    public JSONObject all() {
        JSONObject jsonObject = new JSONObject();
        List<Feedback> feedbacks = feedbackService.list();
        jsonObject.put("msg","data access success");
        jsonObject.put("count", feedbacks.size());
        jsonObject.put("feedbacks",feedbacks);
        return  jsonObject;
    }

    @PostMapping("/add")
    public JSONObject add(Feedback feedback) {
        JSONObject jsonObject = new JSONObject();
        try {
            feedbackService.save(feedback);
            jsonObject.put("code",200);
            jsonObject.put("msg","data insert success");
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("code",-1);
            jsonObject.put("msg","data insert false"+e.getMessage());
            return jsonObject;
        }
    }
}
