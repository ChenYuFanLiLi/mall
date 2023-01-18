package com.example.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mall.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : 陈宇凡
 * @date : 2023/1/18
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
