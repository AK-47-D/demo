package com.example.demo.service;

import com.example.demo.model.People;

import java.util.List;
import java.util.Map;

/**
 * Created by wb-cmx239369 on 2017/9/27.
 */
public interface PeopleService extends BaseService<People,Long>{
    Map<String,List<People>> serviceMadePeople(Integer count) throws Exception;

}
