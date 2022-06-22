package me.cuiyijie.common.security.integration.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 集成登录认证器未找到异常
 *
 * @author cyj976655@gmail.com
 * @date 2022/6/22 22:20
 */
public class AuthenticatorNotFoundException extends AuthenticationException {
    public AuthenticatorNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AuthenticatorNotFoundException(String msg) {
        super(msg);
    }
}
