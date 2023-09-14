package com.pingalax.biz.areaEnergy.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

/**
 * @author zhouxiaotao
 * @Description: 测算业务层实体
 * @date 2023-08-18 14:42
 */
@Data
public class AreaEnergyRequestBo {
    @ApiModelProperty("区域能源ID")
    private Integer id;

    @ApiModelProperty("测算类型：1光伏测算、2储能测算、3光伏+储能测算")
    private Integer type;

    @ApiModelProperty("屋顶面积（㎡）")
    private BigDecimal roofArea;

    @ApiModelProperty("屋顶类型：1彩钢瓦屋顶、2水泥平屋顶")
    private Integer roofType;

    @ApiModelProperty("最佳倾角：1-0度平铺、2-最佳倾角")
    private Integer dipAngle;

    @ApiModelProperty("光伏建设单位投资（元/W）")
    private BigDecimal pvUnitInvest;

    @ApiModelProperty("自用比例")
    private BigDecimal selfUseRatio;

    @ApiModelProperty("储能安装容量（MWh）")
    private BigDecimal essCapacity;

    @ApiModelProperty("运行策略：1一充一放、2两充两放")
    private Integer workingStrategy;

    @ApiModelProperty("储能建设单位投资（元/W）")
    private BigDecimal essUnitInvest;

}
