package com.pingalax.basic.areaEnergy.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zhouxiaotao
 * @Description: 请求参数实体类
 * @date 2023-08-18 11:25
 */
@Data
@AllArgsConstructor
public class AreaEnergyRequest {
    @ApiModelProperty(value = "区域能源ID", required = true)
    private Integer id;

    @ApiModelProperty(value = "测算类型：1光伏测算、2储能测算、3光伏+储能测算")
    private Integer type;

    @ApiModelProperty(value = "屋顶面积（㎡）")
    private BigDecimal roofArea;

    @ApiModelProperty(value = "屋顶类型：1彩钢瓦屋顶、2水泥平屋顶")
    private Integer roofType;

    @ApiModelProperty(value = "最佳倾角：1-0度平铺、2-最佳倾角")
    private Integer dipAngle;

    @ApiModelProperty(value = "光伏建设单位投资（元/W）")
    private BigDecimal pvUnitInvest;

    @ApiModelProperty(value = "自用比例")
    private BigDecimal selfUseRatio;

    @ApiModelProperty(value = "储能安装容量（MWh）")
    private BigDecimal essCapacity;

    @ApiModelProperty(value = "运行策略：1一充一放、2两充两放")
    private Integer workingStrategy;

    @ApiModelProperty(value = "储能建设单位投资（元/W）")
    private BigDecimal essUnitInvest;

}
