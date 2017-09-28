package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.model.People;
import com.example.demo.rocketMQ.PeopleProducer;
import com.example.demo.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wb-cmx239369 on 2017/9/27.
 */
@Controller
public class HellowController {
    @Autowired
    private PeopleService peopleService;

    @GetMapping("hello")
    public String hello(ModelMap modelMap){
        modelMap.addAttribute("time", new Date());
        modelMap.addAttribute("message", "这是测试的内容。。。");
        modelMap.addAttribute("toUserName", "张三");
        modelMap.addAttribute("fromUserName", "老许");
        return "hello";
    }
    @GetMapping("peopleService")
    public String peopleService(){
        return "peopleService";
    }
    @PostMapping("testNew")
    @ResponseBody
    public String testNew(Integer count) throws Exception{
        Map<String,List<People>> map = peopleService.serviceMadePeople(count);
        return "success: 造人服务 ===========" + JSON.toJSONString(map);
    }
}
