package me.cuiyijie.projectbasic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.cuiyijie.projectbasic.entity.User;
import me.cuiyijie.projectbasic.mapper.SysUserMapper;
import me.cuiyijie.projectbasic.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author cuiyijie
 * @since 2022-05-16
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, User> implements ISysUserService {

    @Override
    public User getByUsername(String username) {
        return getOne(new QueryWrapper<User>().eq("username", username));
    }
}
