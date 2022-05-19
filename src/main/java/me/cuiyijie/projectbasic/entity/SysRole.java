package me.cuiyijie.projectbasic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

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
@TableName("sys_role")
@ApiModel(value = "SysRole对象", description = "")
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private String code;

    @ApiModelProperty("备注")
    private String remark;


}
