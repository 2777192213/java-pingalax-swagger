package com.pingalax.biz.areaEnergy.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zhouxiaotao
 * @Description: 储能测算业务数据
 * @date 2023-08-18 10:05
 */
@Data
public class AreaEnergyResponseBo {
    @ApiModelProperty("每年首发电量")
    private BigDecimal oneFirstYearElectricity;

    @ApiModelProperty("上网电价（元/kWh）")
    private BigDecimal gridElectricityPrice;

    @ApiModelProperty("平均用电电价（元/kWh）")
    private BigDecimal averageUsePrice;

    @ApiModelProperty("装机容量（MW）")
    private BigDecimal installedCapacity;

    @ApiModelProperty("投资成本（万元）")
    private BigDecimal investTotal;

    @ApiModelProperty("平均年收益（万元）")
    private BigDecimal averageIncome;

    @ApiModelProperty("全投资静态回收期（年）")
    private BigDecimal paybackPeriod;

    @ApiModelProperty("全投资收益率（%）")
    private BigDecimal returnRate;

    @ApiModelProperty("首年放电量（MWh）")
    private BigDecimal firstYearDischarge;

    @ApiModelProperty("峰谷电价差（元/KWh）")
    private BigDecimal peakValleyDifferencePrice;
}
