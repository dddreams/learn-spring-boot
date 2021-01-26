package com.shure.swagger2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    // 模拟users信息的存储
    static Map<Integer, UserEntity> users = Collections.synchronizedMap(new HashMap<>());

    @GetMapping("/list")
    @ApiOperation(value = "获取用户列表")
    public List<UserEntity> list(){
        List<UserEntity> us = new ArrayList<>(users.values());
        return us;
    }

    @GetMapping("/get/{id}")
    @ApiOperation(value = "获取用户详细信息", notes = "根据用户id来获取用户详细信息")
    public UserEntity getUser(@PathVariable Integer id) {
        return users.get(id);
    }

    @PostMapping("/saveOrUpdate")
    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    public String postUser(@RequestBody UserEntity user) {
        users.put(user.getId(), user);
        return "success";
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "删除用户", notes = "根据用户的id来指定删除对象")
    public String deleteUser(@PathVariable Integer id) {
        users.remove(id);
        return "success";
    }

}
