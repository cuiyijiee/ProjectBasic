package me.cuiyijie.common.security;

import cn.hutool.http.ContentType;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.common.lang.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author cyj976655@gmail.com
 * @date 2022/5/19 22:20
 */
@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("认证失败！未登录！");
        response.setContentType(ContentType.JSON.getValue());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ServletOutputStream outputStream = response.getOutputStream();

        Result result = Result.fail("请先登录！");
        outputStream.write(JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
