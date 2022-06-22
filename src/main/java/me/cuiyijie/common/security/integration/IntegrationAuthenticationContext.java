package me.cuiyijie.common.security.integration;

/**
 *
 * @author cuiyijie
 * @date 2022-06-22 21:10
 */
public class IntegrationAuthenticationContext {

    private static final ThreadLocal<IntegrationAuthentication> context = new ThreadLocal<>();

    public static IntegrationAuthentication get(){
        return context.get();
    }

    public static void set(IntegrationAuthentication integrationAuthentication){
        context.set(integrationAuthentication);
    }

    public static void clear(){
        context.remove();
    }

}
