package me.cuiyijie.projectbasic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.cuiyijie.projectbasic.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author cuiyijie
 * @since 2022-05-16
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_user")
@ApiModel(value = "SysUser对象", description = "")
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String avatar;

    private String email;

    private String city;

    private LocalDateTime lastLogin;

}
