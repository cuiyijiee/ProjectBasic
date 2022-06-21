package me.cuiyijie.projectbasic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author cyj976655@gmail.com
 * @date 2022/6/21 23:26
 */
@Configuration
public class RestTemplateConfig {


    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new CustomMappingJackson2HttpMessageConverter());
        return restTemplate;
    }

}
