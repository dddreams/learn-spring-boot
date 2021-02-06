package com.shure.jdbc;

import lombok.Data;

@Data
public class UserEntity {

    private Integer id;

    private String name;

    private int age;

    private boolean gender;

    private String address;

    public UserEntity(){}

    public UserEntity(String name, int age, boolean gender, String address){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
    }

}
