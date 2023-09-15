package com.pingalax.basic.equipments.dto;

import com.pingalax.common.util.page.BasePage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhouxiaotao
 * @Description: 分页查询设备请求参数
 * @date 2023-08-19 22:50
 */
@Data
@ApiModel("分页查询设备请求参数")
@EqualsAndHashCode(callSuper = true)
public class QueryEquipmentPageRequest extends BasePage {

    @ApiModelProperty(value = "设备所处站点")
    private String equipmentSiteName;

    @ApiModelProperty(value = "设备类型")
    private Integer equipmentType;
}
