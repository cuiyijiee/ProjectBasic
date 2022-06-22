package me.cuiyijie.common.security.integration.authenticator;

import me.cuiyijie.common.security.MyUserDetail;
import me.cuiyijie.common.security.integration.AbstractIntegrationAuthenticator;
import me.cuiyijie.common.security.integration.IntegrationAuthentication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;

/**
 * next+的登录实现
 *
 * @author cyj976655@gmail.com
 * @date 2022/6/22 21:35
 */

@Configuration
public class NextPlusAuthentication extends AbstractIntegrationAuthenticator {
    @Override
    public boolean support(IntegrationAuthentication integrationAuthentication) {
        return false;
    }

    @Override
    public void prepare(IntegrationAuthentication integrationAuthentication) {

    }

    @Override
    public MyUserDetail authenticate(IntegrationAuthentication integrationAuthentication) throws AuthenticationException {
        return null;
    }

    @Override
    public void complete(IntegrationAuthentication integrationAuthentication) {

    }
}
