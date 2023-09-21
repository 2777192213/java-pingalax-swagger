package com.pingalax.ext.category.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author zhouxiaotao
 * @Description: 分类实体
 * @date 2023-09-18 11:02
 */
@Data
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    private Long id;

    //类型 1 菜品分类 2 套餐分类
    @ApiModelProperty(value = "类型")
    private Integer type;

    //分类名称
    @ApiModelProperty(value = "分类名称")
    private String name;

    //顺序
    @ApiModelProperty(value = "顺序")
    private Integer sort;

    //创建时间
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    //更新时间
    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    //创建人
    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    //修改人
    @ApiModelProperty(value = "修改人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    //是否删除
    //@ApiModelProperty(value = "是否删除")
    //private Integer isDeleted;
}