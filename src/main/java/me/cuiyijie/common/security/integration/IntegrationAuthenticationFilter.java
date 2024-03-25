package me.cuiyijie.common.security.integration;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cyj976655@gmail.com
 * @date 2022/6/22 21:24
 */
@Component
public class IntegrationAuthenticationFilter extends GenericFilterBean implements ApplicationContextAware {

    private static final String AUTH_TYPE_PARM_NAME = "authType";
    /**
     * 这里要与配置中登录接口一致
     */
    public static final String LOGIN_PATH = "/login";

    private ApplicationContext applicationContext;

    private Collection<AbstractIntegrationAuthenticator> authenticators;

    private final RequestMatcher requestMatcher;

    public IntegrationAuthenticationFilter() {
        this.requestMatcher = new AntPathRequestMatcher(LOGIN_PATH);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //登录类型
        String authType = request.getParameter(AUTH_TYPE_PARM_NAME);
        //匹配url且登录方式不为空
//        if (StringUtils.isBlank(authType)) {
//            throw new AuthenticatorNotFoundException("登录方式不能为空！");
//        } else if (requestMatcher.matches(request)) {
        if (requestMatcher.matches(request) && StringUtils.isNoneBlank(authType)) {
            //设置集成登录信息
            IntegrationAuthentication integrationAuthentication = new IntegrationAuthentication();
            integrationAuthentication.setAuthType(authType);
            integrationAuthentication.setParameterMap(request.getParameterMap());
            IntegrationAuthenticationContext.set(integrationAuthentication);
            try {
                //预处理
                this.prepare(integrationAuthentication, request);
                //重写request， 覆盖密码. 表示不校验密码
                if (integrationAuthentication.getPassword() != null) {
                    request = new HttpServletRequestWrapper(request) {
                        @Override
                        public String getParameter(String name) {
                            if ("password".equals(name)) {
                                return integrationAuthentication.getPassword();
                            }
                            return super.getParameter(name);
                        }

                        @Override
                        public Map<String, String[]> getParameterMap() {
                            HashMap<String, String[]> newMap = new HashMap<>();
                            newMap.putAll(super.getParameterMap());
                            newMap.put("password", new String[]{integrationAuthentication.getPassword()});
                            return Collections.unmodifiableMap(newMap);
                        }

                        @Override
                        public String[] getParameterValues(String name) {
                            if ("password".equals(name)) {
                                return new String[]{integrationAuthentication.getPassword()};
                            }
                            return super.getParameterValues(name);
                        }
                    };
                }
                filterChain.doFilter(request, response);
                //后置处理
                this.complete(integrationAuthentication);
            } finally {
                IntegrationAuthenticationContext.clear();
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private void prepare(IntegrationAuthentication integrationAuthentication, HttpServletRequest request) {
        //延迟注入 FIXME 这里需要改一下
        if (authenticators == null) {
            synchronized (this) {
                Map<String, AbstractIntegrationAuthenticator> authenticatorMap = applicationContext.getBeansOfType(AbstractIntegrationAuthenticator.class);
                this.authenticators = authenticatorMap.values();
            }
        }
        for (AbstractIntegrationAuthenticator authenticator : this.authenticators) {
            if (authenticator.support(integrationAuthentication)) {
                authenticator.prepare(integrationAuthentication);
            }
        }
    }

    private void complete(IntegrationAuthentication integrationAuthentication) {
        for (AbstractIntegrationAuthenticator authenticator : authenticators) {
            if (authenticator.support(integrationAuthentication)) {
                authenticator.complete(integrationAuthentication);
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
