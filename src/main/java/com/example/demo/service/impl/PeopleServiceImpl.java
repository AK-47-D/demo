package com.example.demo.service.impl;

import com.example.demo.dao.PeopleDao;
import com.example.demo.model.People;
import com.example.demo.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wb-cmx239369 on 2017/9/27.
 */
@Service
public class PeopleServiceImpl implements PeopleService {
    @Autowired
    private PeopleDao peopleDao;

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
}
