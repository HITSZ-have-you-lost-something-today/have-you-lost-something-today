package work.hitsz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 出错页面控制器
 * @author Huang
 * @since 2021-2-4
 */

@Controller
@RequestMapping(value = "/error")
public class ErrorController {

    @GetMapping(value = "/404")
    public String error404() {
        return "error";
    }

    @GetMapping(value = "/500")
    public String error500() {
    return "error";
    }
}
