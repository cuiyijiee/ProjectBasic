package me.cuiyijie.projectbasic;

import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.projectmanager.api.ecology.EcologyApi;
import me.cuiyijie.projectmanager.api.ecology.entity.EcologyUserProfileResp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author cyj976655@gmail.com
 * @date 2022/6/21 23:27
 */
@Slf4j
@SpringBootTest
public class EcologyTest {

    @Autowired
    private EcologyApi ecologyApi;

    @Test
    void contextLoads() {

        //http://hi.joyea.cn:90/sso/oauth2.0/authorize?client_id=356543ea-9f8d-4138-bd89-e798b9284e46&response_type=code&redirect_uri=http://zlgl.yexiang.tech:8080/%23/login#/login
        String accessToken = ecologyApi.getAccessTokenByTicket("ST-748-PgKtPgfhNZKaB7l7yxhz-c01").getAccessToken();
        EcologyUserProfileResp profile = ecologyApi.getUserInfoByToken(accessToken);
        log.info("get profile: {}", profile);
    }

}
