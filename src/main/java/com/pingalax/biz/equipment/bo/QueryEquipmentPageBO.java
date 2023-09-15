package com.pingalax.biz.equipment.bo;

import com.pingalax.common.util.page.BasePage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhouxiaotao
 * @Description: 分页查找设备实体类
 * @date 2023-08-19 18:56
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QueryEquipmentPageBO extends BasePage {
    private String equipmentSiteName;
    private Integer equipmentType;
}
