package com.hw;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class LoginTest {

    @Test
    public void test1(){
        String encode = new BCryptPasswordEncoder().encode("system");
        System.out.println(encode);
    }
}
