package me.cuiyijie.common.security;

import me.cuiyijie.common.lang.Constants;
import me.cuiyijie.common.security.integration.IntegrationAuthenticationFilter;
import me.cuiyijie.common.security.integration.IntegrationUserDetailService;
import me.cuiyijie.common.security.integration.handler.LoginFailureHandler;
import me.cuiyijie.common.security.integration.handler.LoginSuccessHandler;
import me.cuiyijie.common.security.integration.handler.MyAccessDeniedHandler;
import me.cuiyijie.common.security.integration.handler.MyAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

//import me.cuiyijie.projectbasic.service.impl.UserDetailsServiceImpl;

/**
 * @author cyj976655@gmail.com
 * @date 2022/5/19 21:20
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private IntegrationUserDetailService integrationUserDetailService;

    @Autowired
    LoginFailureHandler loginFailureHandler;
    @Autowired
    LoginSuccessHandler loginSuccessHandler;
//    @Autowired
//    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//    @Autowired
//    UserDetailsServiceImpl userDetailsService;


    @Resource
    private IntegrationAuthenticationFilter integrationAuthenticationFilter;

//    @Bean
//    JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
//        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
//        return jwtAuthenticationFilter;
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .userDetailsService(integrationUserDetailService)
                .addFilterBefore(integrationAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)

                .and()
                .authorizeRequests()
                .antMatchers(Constants.URL_WHITELIST).permitAll()
                .anyRequest().authenticated()

//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                //????????????
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new MyAccessDeniedHandler())
                .authenticationEntryPoint(new MyAuthenticationEntryPoint()) //401
                //.authenticationEntryPoint(jwtAuthenticationEntryPoint)

                .and()
        //.addFilter(jwtAuthenticationFilter());
        ;
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }

    /**
     * ????????????????????????
     * DelegatingPasswordEncoder ??????????????????????????????'{????????????}',??????????????????????????????PasswordEncoder??????
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
