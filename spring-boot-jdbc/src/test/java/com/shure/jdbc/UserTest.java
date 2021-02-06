package com.shure.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void testSave(){
        userService.save(new UserEntity("shure", 20, true, "兰州"));
        userService.save(new UserEntity("tom", 14, true, "南京"));
        userService.save(new UserEntity("pipe", 19, true, "上海"));
        userService.save(new UserEntity("onres", 18, false, "北京"));
        Integer id = userService.save(new UserEntity("kive", 21, false, "成都"));

        UserEntity u = userService.getUser(id);
        Assert.isNull(u);
    }

    @Test
    public void testUpdate(){
        userService.update(new UserEntity("onres", 22, false, "杭州"));
    }

    @Test
    public void testFindUser() {
        List<UserEntity> users = userService.findAll("shure");
        Assert.notEmpty(users);
    }

    public void testDelete(){
        userService.delete(3);
    }
}
