package com.example.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mall.entity.User;
import com.example.mall.mapper.UserMapper;
import com.example.mall.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author : 陈宇凡
 * @date : 2023/1/18
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
