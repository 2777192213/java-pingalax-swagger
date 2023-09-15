package com.pingalax.dao.areaEnergy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author zhouxiaotao
 * @Description: 区域能源实体类
 * @date 2023-08-17 17:00
 */
@Data
@TableName("p_area_energy")
public class AreaEnergyEntity {

    private Integer id;

    private String areaName;

    @JsonRawValue
    private Object chargeDischargeType;

    private BigDecimal horizontalRadiation;

    private BigDecimal bestRadiation;

    private BigDecimal gridElectricityPrice;

    private BigDecimal averageUsePrice;

    private BigDecimal peakElectricityPrice;

    private BigDecimal flatElectricityPrice;

    private BigDecimal valleyElectricityPrice;

    private Integer sort;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
