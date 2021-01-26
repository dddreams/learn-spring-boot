package com.shure.thymeleaf;

import lombok.Data;

@Data
public class UserEntity {

    private Integer id;
    private String name;
    private int age;
    private boolean gender;
    private String address;

}
