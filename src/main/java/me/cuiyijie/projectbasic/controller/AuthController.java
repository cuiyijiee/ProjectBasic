package me.cuiyijie.projectbasic.controller;

import cn.hutool.core.map.MapUtil;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.common.lang.Constants;
import me.cuiyijie.common.lang.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author: yjcui3
 * @Date: 2022/5/17 14:34
 */
@Slf4j
@RestController
public class AuthController extends BaseController {

    @Autowired
    private Producer producer;

    /**
     * 图片验证码
     */
    @GetMapping("/captcha")
    public Result captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = producer.createText();
        String key = UUID.randomUUID().toString();
        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        BASE64Encoder encoder = new BASE64Encoder();
        String str = "data:image/jpeg;base64,";
        String base64Img = str + encoder.encode(outputStream.toByteArray());
        // 存储到redis中
        redisUtil.set(Constants.CAPTCHA_KEY + "_" + key,code,120);
        log.info("验证码 -- {} - {}", key, code);
        return Result.success(
                MapUtil.builder()
                        .put("token", key)
                        .put("base64Img", base64Img)
                        .build()
        );
    }
}
