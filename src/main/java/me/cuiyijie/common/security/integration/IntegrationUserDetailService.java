package me.cuiyijie.common.security.integration;

import me.cuiyijie.common.security.MyUserDetail;
import me.cuiyijie.common.security.integration.exception.AuthenticatorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author cyj976655@gmail.com
 * @date 2022/6/22 22:15
 */
@Component
public class IntegrationUserDetailService implements UserDetailsService {

    @Autowired
    private List<AbstractIntegrationAuthenticator> integrationAuthenticators;

    @Resource
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        IntegrationAuthentication integrationAuthentication = IntegrationAuthenticationContext.get();
        if (this.integrationAuthenticators != null) {
            for (AbstractIntegrationAuthenticator authenticator : integrationAuthenticators) {
                if (authenticator.support(integrationAuthentication)) {
                    MyUserDetail myUserDetail =  authenticator.authenticate(integrationAuthentication);
                    //如果密码不为空，覆盖密码， 后期密码不验证
                    if(integrationAuthentication.getPassword() != null) {
                        myUserDetail.setPassword(passwordEncoder.encode(integrationAuthentication.getPassword()));
                        //userDetail.getSysUser().setPassword(passwordEncoder.encode(integrationAuthentication.getPassword()));
                    }
                    return myUserDetail;
                }
            }
        }
        throw new AuthenticatorNotFoundException("未知登录方式:"+integrationAuthentication.getAuthType());
    }
}
