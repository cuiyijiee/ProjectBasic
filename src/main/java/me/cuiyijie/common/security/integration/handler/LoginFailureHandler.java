package me.cuiyijie.common.security.integration.handler;

import cn.hutool.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.common.lang.Result;
import me.cuiyijie.common.utils.JsonBeanConvertUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 将异常信息返回给客户端，同时设置返回ContentType为JSON
 *
 * @author cyj976655@gmail.com
 * @date 2022/5/19 21:05
 */
@Slf4j
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error("登陆异常: ", exception);
        response.setContentType(ContentType.JSON.getValue());
        ServletOutputStream outputStream = response.getOutputStream();
        Result result = Result.fail(
                "Bad credentials".equals(exception.getMessage()) ? "用户名或密码不正确" : exception.getMessage()
        );
        outputStream.write(JsonBeanConvertUtils.beanToJson(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
