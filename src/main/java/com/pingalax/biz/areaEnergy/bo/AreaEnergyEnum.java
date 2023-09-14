package com.pingalax.biz.areaEnergy.bo;


import com.pingalax.common.annotations.SwaggerDisplayEnum;
import com.pingalax.common.interfaces.BaseValidatorEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhouxiaotao
 * @Description: 测算类型
 * @date 2023-08-18 9:50
 */
@Getter
@AllArgsConstructor
@SwaggerDisplayEnum
public enum AreaEnergyEnum implements BaseValidatorEnum {
    PHOTOVOLTAIC_MEASUREMENT(1, "光伏测算"),
    ENERGY_STORAGE_CALCULATION(2, "储能测算"),
    PHOTOVOLTAIC_AND_ENERGY_STORAGE_CALCULATION(3, "光伏+储能测算"),
    ;

    private final Integer type;
    private final String desc;

    @Override
    public String getPrimary() {
        return String.valueOf(this.type);
    }

}
