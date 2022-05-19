package me.cuiyijie.projectbasic.service.impl;

import me.cuiyijie.common.security.AccountUser;
import me.cuiyijie.projectbasic.entity.SysUser;
import me.cuiyijie.projectbasic.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.TreeSet;

/**
 * @author cyj976655@gmail.com
 * @date 2022/5/19 22:32
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ISysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        SysUser sysUser = sysUserService.getByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户名或密码不正确!");
        }
        return new AccountUser(sysUser.getId(), sysUser.getUsername(), sysUser.getPassword(), new TreeSet<>());
    }
}
