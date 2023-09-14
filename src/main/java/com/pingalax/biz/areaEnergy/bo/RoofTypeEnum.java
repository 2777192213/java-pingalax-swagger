package com.pingalax.biz.areaEnergy.bo;


import com.pingalax.common.annotations.SwaggerDisplayEnum;
import com.pingalax.common.interfaces.BaseValidatorEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhouxiaotao
 * @Description: 房屋屋顶枚举类
 * @date 2023-08-28 10:52
 */
@Getter
@AllArgsConstructor
@SwaggerDisplayEnum(id = "type")
public enum RoofTypeEnum implements BaseValidatorEnum {
    COLOR_STEEL_TILE_ROOF(1, "彩钢瓦屋顶"),
    CEMENT_FLAT_ROOF(2, "水泥平屋顶");
    private final Integer type;
    private final String desc;

    @Override
    public String getPrimary() {
        return String.valueOf(this.type);
    }
}
