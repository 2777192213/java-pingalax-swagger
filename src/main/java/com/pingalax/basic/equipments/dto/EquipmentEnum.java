package com.pingalax.basic.equipments.dto;

import com.pingalax.common.annotations.SwaggerDisplayEnum;
import com.pingalax.common.interfaces.BaseValidatorEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhouxiaotao
 * @Description: 设备枚举
 * @date 2023-08-07 9:40
 */
@Getter
@AllArgsConstructor
@SwaggerDisplayEnum
public enum EquipmentEnum implements BaseValidatorEnum {
    STORED_ENERGY(2, "储能"),
    SOLAR_POWER(3, "光伏"),
    FILLING_PILE(4, "充点桩");

    private final Integer type;
    private final String desc;

    @Override
    public String getPrimary() {
        return String.valueOf(this.getType());
    }
}
