package com.pingalax.basic.site.dto;

import com.pingalax.common.annotations.SwaggerDisplayEnum;
import com.pingalax.common.interfaces.BaseValidatorEnum;
import io.swagger.annotations.SwaggerDefinition;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhouxiaotao
 * @Description: 站点状态枚举
 * @date 2023-08-06 23:59
 */
@Getter
@AllArgsConstructor
@SwaggerDisplayEnum
public enum SiteStatusEnum implements BaseValidatorEnum {
    UNKNOWN(0, "未知"),
    UNDER_CONSTRUCTION(1, "建设中"),
    CLOSE_THE_DOWNLINE(5, "关闭下线"),
    UNDER_MAINTENANCE(6, "维护中"),
    NORMAL_USE(50, "正常使用");

    private final Integer type;
    private final String desc;

    @Override
    public String getPrimary() {
        return String.valueOf(this.getType());
    }
}
