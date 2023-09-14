package com.pingalax.common.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhouxiaotao
 * @Description: 返回结果状态码枚举
 * @date 2023-08-08 13:33
 */
@AllArgsConstructor
public enum ResultCode {
    SUCCESS("OK", "成功"),
    FAILED("ERROR", "失败"),
    FAILED_PARAM_ERROR("E400", "参数校验错误"),
    FAILED_SERVER_ERROR("E500", "服务内部处理错误"),
    FAILED_SERVICE_UNAVAILABLE("E503", "服务不可用");
    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
