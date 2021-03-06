package me.cuiyijie.common.security.integration.handler;

import cn.hutool.http.ContentType;
import me.cuiyijie.common.lang.Result;
import me.cuiyijie.common.security.MyUserDetail;
import me.cuiyijie.common.utils.JsonBeanConvertUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author cyj976655@gmail.com
 * @date 2022/5/19 21:37
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType(ContentType.JSON.getValue());
        ServletOutputStream outputStream = response.getOutputStream();
        MyUserDetail myUserDetail = (MyUserDetail) authentication.getPrincipal();
        Result result = Result.success(myUserDetail);
        outputStream.write(JsonBeanConvertUtils.beanToJson(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
