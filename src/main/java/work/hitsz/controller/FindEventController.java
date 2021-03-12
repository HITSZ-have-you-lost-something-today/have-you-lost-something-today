package work.hitsz.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import work.hitsz.entity.FindEvent;
import work.hitsz.entity.User;
import work.hitsz.service.FindEventService;
import work.hitsz.service.UserService;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Huang
 * @since 2021-02-02
 */
@RestController
@RequestMapping("/find-event")
public class FindEventController {
    @Resource
    FindEventService findEventService;
    @Resource
    UserService userService;

    // 读取配置文件的图片储存路径
    @Value(value = "${upload.location}")
    private String filePath;

    // 使用此格式的日期对
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");

    // 返回所有的events数据
    @GetMapping(value = "/getAll")
    public JSONObject getAll() {
        JSONObject jsonObject = new JSONObject();
        List<FindEvent> events = findEventService.list();
        jsonObject.put("code", 200);
        jsonObject.put("msg","data access success");
        jsonObject.put("count", events.size());
        jsonObject.put("data", events);
        return jsonObject;
    }

    // 添加丢失物品信息
    @PostMapping("/add")
    public JSONObject add(@RequestParam("file") MultipartFile file,
                          @RequestParam("userId") String userId,
                            FindEvent event) {
        JSONObject jsonObject = new JSONObject();
        if (file.isEmpty()) {
            jsonObject.put("code", 1024);
            jsonObject.put("msg", "图片上传失败，请重新选择图片");
            return jsonObject;
        }
        // 根据时期创建文件夹，对文件进行分类
        String format = sdf.format(new Date());
        File folder = new File(filePath+format);
        // 若文件夹不存在，则新建一个文件夹
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        String oldName = file.getOriginalFilename();
        // 文件重命名，防止重复
        String newName = UUID.randomUUID().toString()+oldName.substring(oldName.lastIndexOf("."), oldName.length());
        // 文件保存并且将路径写入数据库
        try {
            file.transferTo(new File(folder, newName));
            event.setGmtCreate(LocalDateTime.now());
            event.setThingPhotoUrl("/uploadImg/"+format+newName);
            event.setGmtModified(event.getGmtEvent());
            // 前端传递的数据
            /*
            event.setGmtEvent();
            event.setThingInfo();
            event.setThingType();
            event.setThingWhere();
            event.setUserId();
            */
            boolean rs = findEventService.save(event);
            jsonObject.put("code", 200);
            jsonObject.put("msg","事件创建成功");
            jsonObject.put("success",rs);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    // 用户删除自己发布的事件
    @PostMapping(value = "/del")
    public JSONObject deleteEvent(@RequestParam("eventId") String id,
                                  @RequestParam("userId") String userId) {
        JSONObject jsonObject = new JSONObject();
        FindEvent event = findEventService.getById(Integer.valueOf(id));
        if (event.getUserId() == Long.valueOf(userId)) {
            boolean rs = findEventService.removeById(Integer.valueOf(id));
            jsonObject.put("success", rs);
            jsonObject.put("msg", "删除成功");
            QueryWrapper<FindEvent> queryWrapper = new QueryWrapper<>();
            queryWrapper.ge("userId",Long.valueOf(userId));
            jsonObject.put("events",findEventService.list(queryWrapper));
            return jsonObject;
        } else {
            jsonObject.put("code",1024);
            jsonObject.put("msg", "您没有权限删除该数据");
            return jsonObject;
        }
    }

    // 查询用户已经发布的信息
    @GetMapping(value = "/myEvents")
    public JSONObject myEvents(@RequestParam("userId") String userId) {
        JSONObject jsonObject = new JSONObject();
        Long idKey = Long.valueOf(userId);
        QueryWrapper<FindEvent> findEventQueryWrapper = new QueryWrapper<>();
        findEventQueryWrapper.ge("userId",idKey);
        jsonObject.put("events",findEventService.list(findEventQueryWrapper));
        return jsonObject;
    }

    // 分类查询
    @GetMapping(value = "/type")
    public JSONObject getByType(@RequestParam("type") String type) {
        JSONObject jsonObject = new JSONObject();
        QueryWrapper<FindEvent> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("thingType",type);
        List<FindEvent> events = findEventService.list(queryWrapper);
        try {
            jsonObject.put("code",200);
            jsonObject.put("events",events);
            jsonObject.put("msg","data access success");
        } catch(Exception e) {
            e.printStackTrace();
            jsonObject.put("code",1024);
            jsonObject.put("msg","未知错误"+e.getMessage());
        }
            return jsonObject;
    }



}
