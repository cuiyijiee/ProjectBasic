package me.cuiyijie.common.security.integration.handler;

import cn.hutool.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.common.lang.Result;
import me.cuiyijie.common.utils.JsonBeanConvertUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Author: yjcui3
 * @Date: 2022/6/28 20:51
 */
@Slf4j
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("鉴权失败: ", authException);
        response.setContentType(ContentType.JSON.getValue());
        ServletOutputStream outputStream = response.getOutputStream();
        Result result = Result.fail(HttpStatus.UNAUTHORIZED.value(),"认证失败： " + authException.getMessage(),null);
        outputStream.write(JsonBeanConvertUtils.beanToJson(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
