package com.example.demo.service.impl;

import com.example.demo.dao.PeopleDao;
import com.example.demo.model.People;
import com.example.demo.rocketMQ.PeopleProducer;
import com.example.demo.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wb-cmx239369 on 2017/9/27.
 */
@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleProducer peopleProducer;
    @Autowired
    private PeopleDao peopleDao;

    private static String[] name = {"张三","李四","王五"};

    @Override
    @Transactional
    public People update(People people) {
        return peopleDao.save(people);
    }

    @Override
    @Transactional
    public People save(People people) {
        return peopleDao.save(people);
    }

    @Override
    public List<People> findAll() {
        return peopleDao.findAll();
    }

    @Override
    public People findOne(Long id) {
        return peopleDao.findOne(id);
    }

    @Override
    public Map<String,List<People>> serviceMadePeople(Integer count) throws Exception{
        Map<String,List<People>> map = new HashMap<>();
        for(int i = 0 ;i < count;i++) {
            People people = new People();
            int age = Integer.valueOf(Math.round(Math.random() * 100 + 1) + "");
            people.setAge(age);
            people.setName(name[age % name.length]);
            peopleProducer.sendPeopleMQ(people);
            List<People> peoples = map.get(people.getName());
            if( peoples == null){
                peoples = new ArrayList<>();
                map.put(people.getName(),peoples);
            }
            peoples.add(people);
        }
        return map;
    }
}
