package com.hw.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginResult {
    //用户编号
    private Long id;
    //状态码
    private int code;
    //redis中token令牌对应的key
    private String token;
    //token过期时间
    private Long expireTime;
}
