package com.example.demo.service;

import java.util.List;

/**
 * Created by wb-cmx239369 on 2017/9/27.
 */
public interface BaseService<T,ID> {
    T update(T t);
    T save(T t);
    List<T> findAll();
    T findOne(ID id);
}
