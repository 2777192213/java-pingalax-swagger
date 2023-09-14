package com.pingalax.basic.areaEnergy.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zhouxiaotao
 * @Description: 测算结果实体
 * @date 2023-08-18 14:05
 */
@Data
public class AreaEnergy {
    @ApiModelProperty(value = "区域能源ID")
    private Integer id;

    @ApiModelProperty(value = "区域")
    private String areaName;

    @ApiModelProperty(value = "充放类型：1一充一放、2两充两放")
    private Object chargeDischargeType;

    @ApiModelProperty(value = "水平面总辐照量平均值（kWh/㎡）")
    private BigDecimal horizontalRadiation;

    @ApiModelProperty(value = "最佳斜面总辐照量平均值（kWh/㎡）")
    private BigDecimal bestRadiation;

    @ApiModelProperty(value = "上网电价（元/kWh）")
    private BigDecimal gridElectricityPrice;

    @ApiModelProperty(value = "平均用电电价（元/kWh）")
    private BigDecimal averageUsePrice;

    @ApiModelProperty(value = "峰电价（元/kWh）")
    private BigDecimal peakElectricityPrice;

    @ApiModelProperty(value = "平电价（元/kWh）")
    private BigDecimal flatElectricityPrice;

    @ApiModelProperty(value = "谷电价（元/kWh）")
    private BigDecimal valleyElectricityPrice;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
