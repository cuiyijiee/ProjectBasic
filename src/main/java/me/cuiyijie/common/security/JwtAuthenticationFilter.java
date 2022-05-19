package me.cuiyijie.common.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import me.cuiyijie.common.utils.JwtUtils;
import me.cuiyijie.common.utils.RedisUtil;
import me.cuiyijie.projectbasic.service.ISysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.TreeSet;

/**
 * @author cyj976655@gmail.com
 * @date 2022/5/19 21:44
 */
@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    ISysUserService sysUserService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("jwt filter 开始校验用户信息");
        String jwt = request.getHeader(jwtUtils.getHeader());
        if (StringUtils.isBlank(jwt)) {
            chain.doFilter(request, response);
            return;
        }
        Claims claim = jwtUtils.getClaimByToken(jwt);
        if (claim == null) {
            throw new JwtException("token异常！");
        }
        if (jwtUtils.isTokenExpired(claim)) {
            throw new JwtException("token已过期");
        }
        String username = claim.getSubject();
        log.info("用户-{}，正在登陆！", username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(username, null, new TreeSet<>());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        chain.doFilter(request, response);
    }
}
