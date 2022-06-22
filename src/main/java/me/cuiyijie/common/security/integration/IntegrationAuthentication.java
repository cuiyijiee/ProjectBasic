package me.cuiyijie.common.security.integration;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @Author: yjcui3
 * @Date: 2022/6/22 17:26
 */
@Data
public class IntegrationAuthentication {

    private String authType;

    private String password;

    private Map<String, String[]> parameterMap;

    public String getParameter(String key) {
        if (parameterMap != null) {
            String[] values = parameterMap.get(key);
            if (values != null && values.length > 0) {
                return StringUtils.join(values);
            }
        }
        return null;
    }

}
