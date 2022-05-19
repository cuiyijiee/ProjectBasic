package me.cuiyijie.common.security;

import cn.hutool.http.ContentType;
import cn.hutool.json.JSONUtil;
import me.cuiyijie.common.lang.Result;
import me.cuiyijie.common.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType(ContentType.JSON.getValue());

        ServletOutputStream outputStream = response.getOutputStream();
        String jwt = jwtUtils.generateToken(authentication.getName());
        response.setHeader(jwtUtils.getHeader(), jwt);

        Result result = Result.success("");
        outputStream.write(JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();

    }
}
