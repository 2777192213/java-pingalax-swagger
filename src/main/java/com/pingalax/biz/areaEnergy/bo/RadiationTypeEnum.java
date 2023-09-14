package com.pingalax.biz.areaEnergy.bo;


import com.pingalax.common.annotations.SwaggerDisplayEnum;
import com.pingalax.common.interfaces.BaseValidatorEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhouxiaotao
 * @Description: 角度枚举类
 * @date 2023-08-28 11:06
 */
@Getter
@AllArgsConstructor
@SwaggerDisplayEnum(id = "type")
public enum RadiationTypeEnum implements BaseValidatorEnum {
    ZERO_TILING(1, "0度平铺"),
    OPTIMUM_INCLINATION(2, "最佳倾角");
    private final Integer type;
    private final String desc;

    @Override
    public String getPrimary() {
        return String.valueOf(this.type);
    }
}
