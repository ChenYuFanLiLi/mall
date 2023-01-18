package com.example.mall.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mall.entity.User;
import com.example.mall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : 陈宇凡
 * @date : 2023/1/18
 **/
@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/getUser")
    @ResponseBody
    public String getUser(){
        return "?";
    }

    @GetMapping("/getUserList")
    @ResponseBody
    public Page<User> getUserList(int star){
        Page<User> userPage = new Page<>(star,2);
        return userService.page(userPage);
    }
}
