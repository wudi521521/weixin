package com.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wudi
 * @Description:
 * @date 16:29  2018/1/9
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class TestController {

    @RequestMapping("/index")
    public String index(Map<String,Object> map ){

        log.info("【进行跳转页面】");

        map.put("name","你好江湖");

        List<Map<String,Object>> friends =new ArrayList<Map<String,Object>>();
        Map<String,Object> friend = new HashMap<String,Object>();
        friend.put("name", "张三");
        friend.put("age", 20);
        friends.add(friend);
        friend = new HashMap<String,Object>();
        friend.put("name", "李四");
        friend.put("age", 22);
        friends.add(friend);
        map.put("friends", friends);
        return "hello";
    }

    public static void main(String[] args) {
     String token = "wudi_%s";
        String format = String.format(token, "123456");
        System.out.println("【结果输出】"+format);
    }
}
