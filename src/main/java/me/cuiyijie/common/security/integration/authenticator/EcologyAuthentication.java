package me.cuiyijie.common.security.integration.authenticator;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.common.security.MyUserDetail;
import me.cuiyijie.common.security.integration.AbstractIntegrationAuthenticator;
import me.cuiyijie.common.security.integration.IntegrationAuthentication;
import me.cuiyijie.common.security.integration.enums.LoginType;
import me.cuiyijie.projectbasic.entity.User;
import me.cuiyijie.projectbasic.mapper.SysUserMapper;
import me.cuiyijie.projectmanager.api.ecology.EcologyApi;
import me.cuiyijie.projectmanager.api.ecology.entity.EcologyAccessToken;
import me.cuiyijie.projectmanager.api.ecology.entity.EcologyUserProfileResp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.HashSet;

/**
 * 泛微ecology的登录实现
 *
 * @author cyj976655@gmail.com
 * @date 2022/6/22 21:35
 */
@Slf4j
@Component
public class EcologyAuthentication extends AbstractIntegrationAuthenticator {

    private final static String AUTH_TYPE = LoginType.Ecology.name();

    private static final String CODE_PARM_NAME = "code";
    @Autowired
    private EcologyApi ecologyApi;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public boolean support(IntegrationAuthentication integrationAuthentication) {
        return AUTH_TYPE.equalsIgnoreCase(integrationAuthentication.getAuthType());
    }

    @Override
    public void prepare(IntegrationAuthentication integrationAuthentication) {
        //将验证码作为用户名密码，覆盖默认的密码验证，使得密码验证通过
        //(1、将code值覆盖request中的password参数， 2、code值覆盖UserDetails中的password)
        String code = integrationAuthentication.getParameter(CODE_PARM_NAME);
        integrationAuthentication.setPassword(code);
    }

    @Override
    public MyUserDetail authenticate(IntegrationAuthentication integrationAuthentication) throws AuthenticationException {
        String code = integrationAuthentication.getParameter(CODE_PARM_NAME);
        boolean isDev = StringUtils.endsWithIgnoreCase("true", integrationAuthentication.getParameter("dev"));
        if (StringUtils.isBlank(code)) {
            throw new BadCredentialsException("泛微授权码为空！");
        }
        EcologyUserProfileResp ecologyUserProfileResp;
        try {
            EcologyAccessToken accessToken = ecologyApi.getAccessTokenByTicket(code, isDev);
            ecologyUserProfileResp = ecologyApi.getUserInfoByToken(accessToken.getAccessToken());
        } catch (Exception exception) {
            log.error("泛微ecology认证失败:", exception);
            throw new AuthenticationServiceException("泛微ecology认证异常！");
        }

        User user = sysUserMapper.selectOne(new QueryWrapper<User>().eq("user_name", ecologyUserProfileResp.getAttributes().getMobile()));
        if (user == null) {
            throw new AuthenticationServiceException("该用户没有同步，请联系管理员进行同步！");
        }
        return MyUserDetail.build(user, new HashSet<>(), new HashSet<>());
    }

    @Override
    public void complete(IntegrationAuthentication integrationAuthentication) {

    }
}
