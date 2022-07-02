package me.cuiyijie.projectbasic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@TableName("user")
@ApiModel(value = "User对象", description = "泛微库用户信息")
public class User extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("自增ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户登录名")
    private String userName;

    @ApiModelProperty("用户姓名")
    private String nickName;

    @JsonIgnore
    private String pwd;

    private String deptNo;

    private String deptName;

    private String companyNo;

    private String companyName;

    private Boolean isAdmin;

    private Boolean isExternal;

    private String email;

    private String phone;

    private LocalDate updateDay;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private Boolean isDelete;

    private Boolean status;
}
