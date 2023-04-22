package com.hw.controller;


import com.hw.entity.User;
import com.hw.service.IUserService;
import com.hw.utils.UniformResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author hw
 * @since 2023-04-21
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/listAll")
    public UniformResult<List<User>> listAll(){
        List<User> list = userService.list();
        return UniformResult.ok(list);
    }

}

