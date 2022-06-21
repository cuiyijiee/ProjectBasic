package me.cuiyijie.projectmanager.api.ecology;

import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.projectmanager.api.ecology.entity.JoyeaAccessToken;
import me.cuiyijie.projectmanager.api.ecology.entity.JoyeaUserProfile;
import me.cuiyijie.projectmanager.api.ecology.entity.JoyeaUserProfileResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
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

    @Autowired
    RestTemplate restTemplate;

    public JoyeaAccessToken getAccessTokenByTicket(String ticket) {
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("client_id", clientId);
        form.add("client_secret", clientSecret);
        form.add("grant_type", "authorization_code");
        form.add("redirect_uri", redirectUrl);
        form.add("code", ticket);
        JoyeaAccessToken result = restTemplate.postForObject(accessTokenUrl, form, JoyeaAccessToken.class);
        return result;
    }

    public JoyeaUserProfile getUserInfoByToken(String token) {
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("access_token", token);
        JoyeaUserProfileResp result = restTemplate.postForObject(profileUrl, form, JoyeaUserProfileResp.class);
        return result.getAttributes();
    }
}
