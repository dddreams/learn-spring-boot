package com.shure.thymeleaf;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {

    UserEntity save(){
        UserEntity user = new UserEntity();
        user.setId(new Random().nextInt(1000)); // 利用随机数模拟主键
        user.setName("Shure");
        user.setGender(true);
        user.setAge(20);
        user.setAddress("甘肃");
        return user;
    }

}
