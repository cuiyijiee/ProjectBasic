package me.cuiyijie.projectbasic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import me.cuiyijie.common.utils.RedisUtil;
import me.cuiyijie.projectbasic.entity.SysUser;
import me.cuiyijie.projectbasic.mapper.SysUserMapper;
import me.cuiyijie.projectbasic.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public SysUser getByUsername(String username) {
        return getOne(new QueryWrapper<SysUser>().eq("username", username));
    }
}
