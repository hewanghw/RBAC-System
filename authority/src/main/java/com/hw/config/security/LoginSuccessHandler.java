package com.hw.config.security;

import com.alibaba.fastjson.JSONObject;
import com.hw.config.redis.RedisService;
import com.hw.entity.User;
import com.hw.utils.JwtUtils;
import com.hw.utils.LoginResult;
import com.hw.utils.ResultCode;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

// 用户认证成功处理类
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtUtils jwtUtils;
    private final RedisService redisService;
    public LoginSuccessHandler(JwtUtils jwtUtils, RedisService redisService) {
        this.jwtUtils = jwtUtils;
        this.redisService = redisService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //设置客户端的响应的内容类型
        response.setContentType("application/json;charset=UTF-8");
        //获取当登录用户信息
        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        User user = customUserDetail.getUser();
        //生成token
        String token = jwtUtils.generateToken(user);
        //设置token签名密钥及过期时间
        long expireTime = Jwts.parser() //获取DefaultJwtParser对象
                .setSigningKey(jwtUtils.getSecret()) //设置签名的密钥
                .parseClaimsJws(token.replace("jwt_", ""))
                .getBody().getExpiration().getTime();//获取token过期时间
        //创建登录结果对象
        LoginResult loginResult = new LoginResult(user.getId(),
                ResultCode.SUCCESS,token,expireTime);
        String result = JSONObject.toJSONString(loginResult);
        //把生成的token存到redis
        String tokenKey = "token_" + token;
        redisService.set(tokenKey, token,jwtUtils.getExpiration() / 1000);
        //获取输出流
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
