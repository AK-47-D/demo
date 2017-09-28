package com.example.demo.dao;

import com.example.demo.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wb-cmx239369 on 2017/9/27.
 */
@Repository
public interface PeopleDao extends JpaRepository<People,Long> {
}
