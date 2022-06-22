package me.cuiyijie.common.security.integration;

import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.common.security.MyUserDetail;
import org.springframework.security.core.AuthenticationException;

/**
 * @Author: yjcui3
 * @Date: 2022/6/22 17:23
 */
@Slf4j
public abstract class AbstractIntegrationAuthenticator {


    public abstract boolean support(IntegrationAuthentication integrationAuthentication);

    /**
     * 前置流程， 主要是将参数一些预处理
     * 例如如果不需要默认的密码验证，将integrationAuthentication password字段塞入任一值
     *
     * @param integrationAuthentication
     */
    public abstract void prepare(IntegrationAuthentication integrationAuthentication);

    /**
     * 验证用户，此方法将在UserDetailService中调用
     * 1、参数校验 2、验证用户登录是否合法 3、加载用户信息
     *
     * @param integrationAuthentication
     * @return
     */
    public abstract MyUserDetail authenticate(IntegrationAuthentication integrationAuthentication) throws AuthenticationException;

    /**
     * 后置流程，目前无逻辑
     *
     * @param integrationAuthentication
     */
    public abstract void complete(IntegrationAuthentication integrationAuthentication);

}
