package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by wb-cmx239369 on 2017/9/27.
 */
@Entity
public class People {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer age;

    public People(){

    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof People){
            return ((People)obj).getName().equals(this.getName());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 17 * this.getName().length();
    }
}
