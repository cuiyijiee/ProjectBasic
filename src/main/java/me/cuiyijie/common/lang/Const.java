package me.cuiyijie.common.lang;

/**
 * @Author: yjcui3
 * @Date: 2022/5/17 14:45
 */
public class Const {

    public final static String CAPTCHA_KEY = "captcha";

    public final static Integer STATUS_ON = 0;
    public final static Integer STATUS_OFF = 1;

    public static final String DEFAULT_PASSWORD = "888888";
    public static final String DEFAULT_AVATAR = "https://image-1300566513.cos.ap-guangzhou.myqcloud.com/upload/images/5a9f48118166308daba8b6da7e466aab.jpg";

    public static final String[] URL_WHITELIST = {
            "/webjars/**",
            "/favicon.ico",

            "/captcha",
            "/login",
            "/logout",
    };

}
