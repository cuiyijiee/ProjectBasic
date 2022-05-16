package me.cuiyijie.projectbasic.controller;

import me.cuiyijie.projectbasic.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cyj976655@gmail.com
 * @date 2022/5/16 22:53
 */
@RestController
public class TestController {

    @Autowired
    ISysUserService userService;

    @GetMapping("/test")
    public Object test() {
        return userService.list();
    }

}
