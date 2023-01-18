package com.example.mall.filter;

import com.example.mall.service.impl.UserDetailsServiceImpl;
import com.example.mall.utils.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author : 陈宇凡
 * @date : 2023/1/18
 **/
public class JwtRequestFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(JwtRequestFilter.class);
//    public static final String BEARER = "Bearer ";

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null && JwtTokenUtil.validateJWT(jwt)) {
                String username = JwtTokenUtil.getUserName(jwt);

                // 如果令牌存在(存在逻辑自己编写)，则加载令牌
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception e) {
            log.error("无法设置用户认证:{}", e);
        }
        filterChain.doFilter(request, response);
    }
    /**
     * 从 Authorization 标头中，提取令牌
     *
     */
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader(JwtTokenUtil.HEADER);

        if (StringUtils.hasText(headerAuth)) {
            return headerAuth;
        }
        return null;
    }
}
