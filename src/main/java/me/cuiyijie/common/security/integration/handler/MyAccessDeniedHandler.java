package me.cuiyijie.common.security.integration.handler;

import cn.hutool.http.ContentType;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.common.lang.Result;
import me.cuiyijie.common.utils.JsonBeanConvertUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Author: yjcui3
 * @Date: 2022/6/28 20:54
 */
@Slf4j
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("访问受限: ", accessDeniedException);
        response.setContentType(ContentType.JSON.getValue());
        ServletOutputStream outputStream = response.getOutputStream();
        Result result = Result.fail(HttpStatus.FORBIDDEN.value(),"访问受限： " + accessDeniedException.getMessage(),null);
        outputStream.write(JsonBeanConvertUtils.beanToJson(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
