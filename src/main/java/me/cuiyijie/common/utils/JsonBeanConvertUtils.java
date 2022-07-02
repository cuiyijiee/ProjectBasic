package me.cuiyijie.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: yjcui3
 * @Date: 2022/6/30 20:00
 */
@Slf4j
public class JsonBeanConvertUtils {

    private volatile static ObjectMapper objectMapper;

    public static <T> T jsonToBean(String json, Class<T> clazz) {
        try {
            ObjectMapper objectMapper = getObjectMapper();
            T bean = (T) objectMapper.readValue(json, clazz);
            return bean;
        } catch (Exception exception) {
            log.error("json转换对象失败：", exception);
        }
        return null;
    }

    public static <T> String beanToJson(T bean) {
        try {
            ObjectMapper objectMapper = getObjectMapper();
            String json = objectMapper.writeValueAsString(bean);

            return json;
        } catch (Exception exception) {
            log.error("对象转换json失败：", exception);
        }
        return "{}";
    }

    /**
     * 获取objectMapper的单例，与controller返回json一致
     *
     * @return
     */
    private static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            synchronized (JsonBeanConvertUtils.class) {
                if (objectMapper == null) {
                    objectMapper = SpringContextUtils.getBean(ObjectMapper.class);
                }
            }
        }
        return objectMapper;
    }

}
