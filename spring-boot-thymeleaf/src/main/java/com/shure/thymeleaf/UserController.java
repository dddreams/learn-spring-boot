package com.shure.thymeleaf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String index(ModelMap map){
        List<UserEntity> users = new ArrayList<UserEntity>();
        users.add(userService.save());
        users.add(userService.save());
        users.add(userService.save());
        map.addAttribute("users", users);
        map.addAttribute("title", "真实的用户列表");
        return "index";
    }

}
