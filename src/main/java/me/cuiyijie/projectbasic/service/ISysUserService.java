package me.cuiyijie.projectbasic.service;

import me.cuiyijie.projectbasic.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cuiyijie
 * @since 2022-05-16
 */
public interface ISysUserService extends IService<User> {

    User getByUsername(String username);
}
