package me.cuiyijie.projectbasic.controller;

import me.cuiyijie.common.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: yjcui3
 * @Date: 2022/5/17 14:34
 */
public class BaseController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    RedisUtil redisUtil;
}
