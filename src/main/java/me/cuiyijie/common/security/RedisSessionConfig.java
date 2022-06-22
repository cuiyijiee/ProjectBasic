package me.cuiyijie.common.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

/**
 * @author cyj976655@gmail.com
 * @date 2022/6/22 22:39
 */
@Configuration
@EnableRedisHttpSession(
        maxInactiveIntervalInSeconds = 24 * 3600,//超时时间，24小时
        redisNamespace = "joyea:spring:session"//在redis上的命名空间，自定义防止不同应用的key混在一起
)
public class RedisSessionConfig {

    /**
     * 定义前端凭据的传输方式 token or cookie
     * HeaderHttpSessionIdResolver 解析Header内对应token
     * CookieHttpSessionIdResolver SpringBoot默认方式，存储一个“SESSION”的cookie
     *
     * @return
     */
    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        //使用token方式，解析header中"X-Auth-Token"，
        return new HeaderHttpSessionIdResolver("X-Token");
        //使用cookie方式，解析cookie中的“SESSION”
        //return new CookieHttpSessionIdResolver();
    }
}
