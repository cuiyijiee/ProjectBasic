package me.cuiyijie.projectbasic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author cyj976655@gmail.com
 * @date 2022/5/16 21:38
 */
@Data
public class BaseEntity implements Serializable {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Boolean enabled;

}
