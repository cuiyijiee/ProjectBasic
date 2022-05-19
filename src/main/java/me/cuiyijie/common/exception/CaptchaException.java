package me.cuiyijie.common.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * @author cyj976655@gmail.com
 * @date 2022/5/19 21:14
 */
public class CaptchaException extends AuthenticationException {
    public CaptchaException(String msg) {
        super(msg);
    }
}
