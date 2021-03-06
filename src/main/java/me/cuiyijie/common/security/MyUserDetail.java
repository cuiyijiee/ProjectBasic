package me.cuiyijie.common.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import me.cuiyijie.projectbasic.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: yjcui3
 * @Date: 2022/6/22 17:32
 */
@Data
public class MyUserDetail implements UserDetails {


    private User user;

    @JsonIgnore
    private String password;
    @JsonIgnore
    private String username;
    @JsonIgnore
    private Collection<? extends GrantedAuthority> authorities;
    @JsonIgnore
    private boolean enabled;

    public static MyUserDetail build(User sysUser, Set<String> roles, Set<String> resources){
        MyUserDetail userDetail = new MyUserDetail();
        userDetail.user = sysUser;
        userDetail.password = sysUser.getPwd();
        userDetail.username = sysUser.getUserName();
        //权限相关
        Set<GrantedAuthority> authorities = new HashSet<>();
        Set<String> permissions = new HashSet<>();
        //角色
        for (String role : roles) {
            permissions.add( "ROLE_"+ role);
        }
        //资源
        for (String resource : resources) {
            if(StringUtils.isNotBlank(resource)){
                permissions.add(resource);
            }
        }
        userDetail.authorities = AuthorityUtils.createAuthorityList(permissions.toArray(new String[0]));
        return userDetail;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
