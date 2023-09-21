package com.pingalax.dao.flavor.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhouxiaotao
 * @Description: 菜品口味实体类
 * @date 2023-09-20 13:45
 */
@Data
@TableName("dish_flavor")
public class FlavorEntity {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty("菜品id")
    //菜品id
    private Long dishId;

    @ApiModelProperty("口味名称")
    //口味名称
    private String name;

    @ApiModelProperty("口味数据list")
    //口味数据list
    private String value;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty("创建者")
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @ApiModelProperty("更新者")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    //@ApiModelProperty("口味数据list")
    //是否删除
    //private Integer isDeleted;
}
