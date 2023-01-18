package com.example.mall.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Map;

/**
 * @author : 陈宇凡
 * @date : 2023/1/18
 **/
@Configuration
@Component
@Slf4j
public class JwtTokenUtil {
    /**
     * 密钥
     */
    private static final String SIGN = "asdfghjkl";

    public static final String HEADER="Authorization";

    /**
     * @param map : 写入payload中的数据
     * @return : token
     */
    public static String createToken(Map<String, String> map) {
        // 默认7天
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7);

        // payload
        JWTCreator.Builder tokenBuilder = JWT.create();
        map.forEach(tokenBuilder::withClaim);

        tokenBuilder.withExpiresAt(instance.getTime());
        return tokenBuilder.sign(Algorithm.HMAC256(SIGN));
    }

    /**
     * @param map : 写入payload中的数据
     * @param day : token过期天数
     * @return : token
     */
    public static String createToken(Map<String, String> map, int day) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, day);

        // payload
        JWTCreator.Builder tokenBuilder = JWT.create();
        map.forEach(tokenBuilder::withClaim);

        tokenBuilder.withExpiresAt(instance.getTime());
        return tokenBuilder.sign(Algorithm.HMAC256(SIGN));
    }

    /**
     * 获取token信息
     * @param token : token
     * @return : token信息
     */
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }

    /**
     * 获取用户名
     * @param token token
     * @return userName
     */
    public static String getUserName(String token){
        return verify(token).getClaims().get("userName").asString();
    }

    /**
     * 校验token
     * @param token token
     * @return Boolean
     */
    public static Boolean validateJWT(String token){
        try {
            JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
            return true;
        } catch (SignatureVerificationException e) {
            log.error("签名不一致异常",e.getMessage());
        } catch (TokenExpiredException e) {

            log.error("令牌过期异常",e.getMessage());
        } catch (AlgorithmMismatchException e) {

            log.error("算法不匹配异常",e.getMessage());
        } catch (InvalidClaimException e) {

            log.error("失效的payload异常",e.getMessage());
        } catch (Exception e) {

            log.error("token无效",e.getMessage());
        }
        return false;
    }
}
