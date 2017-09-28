package com.example.demo.controller;

import com.example.demo.model.People;
import com.example.demo.rocketMQ.PeopleProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by wb-cmx239369 on 2017/9/27.
 */
@Controller
public class HellowController {
    @Autowired
    private PeopleProducer peopleProducer;

    @GetMapping("hello")
    public String hello(ModelMap modelMap){
        modelMap.addAttribute("time", new Date());
        modelMap.addAttribute("message", "这是测试的内容。。。");
        modelMap.addAttribute("toUserName", "张三");
        modelMap.addAttribute("fromUserName", "老许");
        return "hello";
    }
    @GetMapping("testNew")
    @ResponseBody
    public String testNew() throws Exception{
        People people = new People();
        people.setAge(24);
        people.setName("陈明熙");
        peopleProducer.sendPeopleMQ(people);
        return "success";
    }
}
