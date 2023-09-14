package com.pingalax.biz.areaEnergy.bo;

import com.pingalax.common.annotations.SwaggerDisplayEnum;
import com.pingalax.common.interfaces.BaseValidatorEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhouxiaotao
 * @Description: 充电类型枚举类
 * @date 2023-08-28 11:03
 */
@Getter
@AllArgsConstructor
@SwaggerDisplayEnum(id = "type")
public enum ChargeTypeEnum implements BaseValidatorEnum {
    ONE_CHARGE_AND_ONE_RELEASE(1, "一充一放"),
    TWO_CHARGE_AND_TWO_DISCHARGE(2, "两充两放"),
    ;
    private final Integer type;
    private final String desc;

    @Override
    public String getPrimary() {
        return String.valueOf(this.type);
    }
}
