package me.cuiyijie.common.security;

import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.common.exception.CaptchaException;
import me.cuiyijie.common.lang.Const;
import me.cuiyijie.common.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录接口验证码过滤器
 * 目的在于让客户端在请求登录接口的时候校验验证码是否正确
 *
 * @author cyj976655@gmail.com
 * @date 2022/5/19 21:03
 */
@Slf4j
@Component
public class CaptchaFilter extends OncePerRequestFilter {

    private final String loginUrl = "/login";

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    LoginFailureHandler loginFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURI();
        if (loginUrl.equals(url) && request.getMethod().equalsIgnoreCase(HttpMethod.POST.toString())) {
            log.info("获取到login链接，正在校验验证码 -- " + url);
            try {
                validate(request);
            } catch (CaptchaException exception) {
                log.error("验证码校验失败：", exception);
                // 交给登录失败处理器处理
                loginFailureHandler.onAuthenticationFailure(request, response, exception);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void validate(HttpServletRequest request) {
        //登录请求采用POST表单进行登录，取出验证码参数进行校验
        String code = request.getParameter("code");
        String token = request.getParameter("token");

        if (StringUtils.isBlank(code) || StringUtils.isBlank(token)) {
            throw new CaptchaException("验证码不能为空");
        }
        if (!code.equals(redisUtil.get(Const.CAPTCHA_KEY + "_" + token))) {
            throw new CaptchaException("验证码不正确");
        }
        // 一次性使用
        redisUtil.del(Const.CAPTCHA_KEY + "_" + token);
    }
}
