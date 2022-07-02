package me.cuiyijie.common.lang;

/**
 * @Author: yjcui3
 * @Date: 2022/5/17 14:45
 */
public class Constants {

    public final static String CAPTCHA_KEY = "captcha";

    public final static Integer STATUS_ON = 0;
    public final static Integer STATUS_OFF = 1;

    /**时间格式**/
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**日期格式**/
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    /**时钟格式**/
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";


    public static final String[] URL_WHITELIST = {
            "/webjars/**",
            "/favicon.ico",

            "/captcha",
            "/login",
            "/logout",

            //swagger文档
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs"
    };

}
