package com.pingalax.common.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author zhouxiaotao
 * @Description: 异常枚举
 * @date 2023-08-08 15:43
 */
@AllArgsConstructor
@Getter
public enum ResultExceptionEnum {
    SITE_NOT_EXIST_FILE("E400101015", "站点名已存在", null),
    SITE_NOT_EXIST_FILE_IDS("E400101016", "站点ID已存在", null),
    SITE_DOES_NOT_EXIST("E400101017", "站点不存在", null),
    DEVICE_EXISTS_SN("E400101018", "设备SN码已存在", null),
    EQUIPMENT_DOES_NOT_EXIST("E400101019", "设备不存在", null),
    EQUIPMENT_EXIST("E400101020", "设备已存在", null),
    USER_NAME_NOT_EXIST("E400101021", "用户不存在", null),
    PASSWORD_NOT_EXIST("E400101022", "密码不正确", null),
    USER_UNAVAILABLE("E400101023", "用户不可用", null),
    FAILED_IS_NULL_ESS_CAPACITY("E400101017", "储能安装容量须不能为NULL", null),
    FAILED_IS_NULL_ESS_UNIT_INVEST("E400101018", "储能建设单位投资不能为NULL", null),
    FAILED_IS_NULL_SELF_USE_RATION("E400101019", "自用比例不能为NULL", null),
    FAILED_IS_NULL_PV_UNIT_INVEST("E400101020", "光伏建设单位投资不能为NULL", null),
    FAILED_IS_NULL_ROOF_AREA("E400101021", "屋顶面积不能为NULL", null),
    FAILED_NOT_EXIST_AREA_ID("E400101022", "区域ID不存在", null),
    FAILED_IS_NULL_CALCULATION_MODEL("E400101023", "测算模型不能为NULL", null),
    FAILED_IS_NULL_RADIATION("E400101024", "最佳角度不能为NULL", null),
    FAILED_IS_NULL_ROOF_TYPE("E400101025", "房屋类型不能为NULL", null),
    FAILED_IS_NULL_TYPE("E400101026", "测算类型不能为NULL", null),
    FAILED_IS_NULL_WORKING_STRATEGY("E400101027", "运行策略不能为NULL", null),
    LOGIN_SUCCESS("E400101028", "登录成功", null),
    ;
    private final String code;
    private final String desc;
    private final String idx;
}
