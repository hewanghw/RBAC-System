package com.hw.config.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    //存缓存
    public void set(String key ,String value,Long timeOut){
        redisTemplate.opsForValue().set(key,value,timeOut, TimeUnit.SECONDS);
    }
    //取缓存
    public String get(String key){
        return (String) redisTemplate.opsForValue().get(key);
    }
    //清除缓存
    public void del(String key){
        redisTemplate.delete(key);
    }
}
