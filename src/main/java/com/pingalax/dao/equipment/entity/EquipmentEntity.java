package com.pingalax.dao.equipment.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author zhouxiaotao
 * @Description: 设备实体
 * @date 2023-08-09 14:47
 */
@Data
@TableName("p_equipment")
public class EquipmentEntity {
    private String equipmentSiteName;

    private String equipmentName;

    private String equipmentSnCode;

    private Integer equipmentType;
}
