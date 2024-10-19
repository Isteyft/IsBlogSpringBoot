package com.isteyft.controller;


import com.isteyft.pojo.Result;
import com.isteyft.pojo.User;
import com.isteyft.service.UserService;
import com.isteyft.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v1")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        log.info("登录: {}", user);
        User e = userService.login(user);
        //登录成功,生成令牌,下发令牌
        if (e != null){
            Map<String, Object> claims = new HashMap<>();
            claims.put("username", e.getUsername());

            String jwt = JwtUtils.generateJwt(claims); //jwt包含了当前登录的员工信息
            return Result.success(jwt);
        }
        //登录失败, 返回错误信息
        return Result.error("用户名或密码错误");
    }

    @PostMapping("/reg")
    public Result reg(@RequestBody User user){
        log.info("注册: {}", user);
        Boolean e = userService.reg(user);
        if (e){
            return Result.success("注册成功");
        }else {
            return Result.error("注册错误");
        }
    }

}
