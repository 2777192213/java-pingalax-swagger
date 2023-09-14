package com.pingalax.basic.equipments.dto;

import com.pingalax.common.annotations.AsserEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * @author zhouxiaotao
 * @Description: 设备实体
 * @date 2023-08-07 9:31
 */
@Data
@ApiModel("设备")
public class Equipment {
    @ApiModelProperty(value = "所属站点")
    private String equipmentSiteName;

    @ApiModelProperty(value = "设备名称")
    @NotNull(message = "设备名称不能为空")
    private String equipmentName;

    @ApiModelProperty(value = "设备SN")
    @NotNull(message = "设备SN不能为空")
    private String equipmentSnCode;

    //@ApiModelProperty(value = "设备类型:2-储能、3-光伏、4-充电桩", example = "2", required = true)
    @ApiModelProperty(value = "设备类型:2-储能、3-光伏、4-充电桩", required = true)
    @AsserEnum(value = EquipmentEnum.class, message = "设备类型不正确")
    private Integer equipmentType;
}
