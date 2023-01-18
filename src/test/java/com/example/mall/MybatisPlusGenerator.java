package com.example.mall;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.mall.utils.MyBatisPlusCodeGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.HashMap;

/**
 * @author : 陈宇凡
 * @date : 2023/1/18
 **/
@SpringBootTest
public class MybatisPlusGenerator {
//    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = ;

    @Autowired
    private MyBatisPlusCodeGenerator myBatisPlusCodeGenerator;
//    @Autowired
//    private UserMapper userMapper;
    @Test
    public void test(){
//        List<User> userList = userMapper.selectList(null);
//        Assert.assertEquals(5, userList.size());
//        userList.forEach(System.out::println);
    }

    @Test
    public void token(){
        HashMap<String, String> map = new HashMap<>();
        map.put("username","admin");
        map.put("password","123456");
        // 默认7天
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7);

        // payload
        JWTCreator.Builder tokenBuilder = JWT.create();
        map.forEach(tokenBuilder::withClaim);
        tokenBuilder.withExpiresAt(instance.getTime());
        String token=tokenBuilder.sign(Algorithm.HMAC256("asdfghjkl"));
        System.out.printf(token);
        DecodedJWT decodedJWT=JWT.require(Algorithm.HMAC256("asdfghjkl")).build().verify(token);
        System.out.printf(String.valueOf(decodedJWT));
    }

    @Test
    public void generator(){
        myBatisPlusCodeGenerator.generator();
    }
}
