package com.example.mall.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author : 陈宇凡
 * @date : 2023/1/18
 **/
@Data
@TableName(value = "user")
public class User {
    private Long id;
    private String userName;
    private String passWord;
    private String email;
}
