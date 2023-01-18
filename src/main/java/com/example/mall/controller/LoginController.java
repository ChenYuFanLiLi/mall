package com.example.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mall.entity.User;
import com.example.mall.service.IUserService;
import com.example.mall.utils.JwtTokenUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @author : 陈宇凡
 * @date : 2023/1/18
 **/
@Controller
public class LoginController {
    @Autowired
    private IUserService userService;

    @GetMapping("/login")
    @ResponseBody
    public String login(String userName, String passWord, HttpServletResponse response){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name",userName);
        User sqlUser = userService.getOne(userQueryWrapper);
        String jwt="";
        if (passWord.equals(sqlUser.getPassWord())){
            HashMap<String, String> tokenMap = new HashMap<>();
            tokenMap.put("userName",userName);
            tokenMap.put("id",sqlUser.getId().toString());
            jwt = JwtTokenUtil.createToken(tokenMap);
        }
        response.setHeader(JwtTokenUtil.HEADER,jwt);
        return jwt;
    }
}
