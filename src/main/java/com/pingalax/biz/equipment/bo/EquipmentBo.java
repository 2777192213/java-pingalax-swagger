package com.pingalax.biz.equipment.bo;

import lombok.Data;

import java.io.Serializable;


/**
 * @author zhouxiaotao
 * @Description: 设备业务层实体
 * @date 2023-08-09 14:39
 */
@Data
//实现实例化类
public class EquipmentBo implements Serializable {
    private String equipmentSiteName;

    private String equipmentName;

    private String equipmentSnCode;

    private Integer equipmentType;
}
