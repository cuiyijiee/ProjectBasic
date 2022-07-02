package me.cuiyijie.projectmanager.api.ecology;

import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.projectmanager.api.ecology.entity.EcologyAccessToken;
import me.cuiyijie.projectmanager.api.ecology.entity.EcologyUserProfileResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 泛微ecology oauth2登录相关接口
 * 1.前端通过跳转到泛微授权界面，
 * 2.授权成功之后会将ticket回调到我们的前端页面，
 * 3.前端将ticket回传给后端，
 * 4.后端通过ticket获取到accessToken，
 * 5.进而获取到用户信息
 *
 * @author cyj976655@gmail.com
 * @date 2022/6/21 23:23
 */
@Slf4j
@Component
public class EcologyApi {

    @Value("${joyea.ecology.token.url}")
    private String accessTokenUrl;

    @Value("${joyea.ecology.profile.url}")
    private String profileUrl;

    @Value("${joyea.ecology.redirect.url}")
    private String redirectUrl;

    @Value("${joyea.ecology.clientId}")
    private String clientId;

    @Value("${joyea.ecology.clientSecret}")
    private String clientSecret;

    @Value("${joyea.ecology-dev.redirect.url}")
    private String devRedirectUrl;

    @Value("${joyea.ecology-dev.clientId}")
    private String devClientId;

    @Value("${joyea.ecology-dev.clientSecret}")
    private String devClientSecret;

    @Autowired
    RestTemplate restTemplate;

    public EcologyAccessToken getAccessTokenByTicket(String ticket, boolean isDev) {
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "authorization_code");
        if(isDev) {
            form.add("client_id", devClientId);
            form.add("client_secret", devClientSecret);
            form.add("redirect_uri", devRedirectUrl);
        }else{
            form.add("client_id", clientId);
            form.add("client_secret", clientSecret);
            form.add("redirect_uri", redirectUrl);
        }

        form.add("code", ticket);
        EcologyAccessToken result = restTemplate.postForObject(accessTokenUrl, form, EcologyAccessToken.class);
        return result;
    }

    public EcologyUserProfileResp getUserInfoByToken(String token) {
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("access_token", token);
        return restTemplate.postForObject(profileUrl, form, EcologyUserProfileResp.class);
    }
}
