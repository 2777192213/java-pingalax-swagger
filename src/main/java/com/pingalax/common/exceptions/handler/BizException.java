package com.pingalax.common.exceptions.handler;

/**
 * @author zhouxiaotao
 * @Description: 实现层相关异常
 * @date 2023-09-06 22:39
 */
public class BizException extends Exception {
    private String code;
    private String desc;

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BizException(String code, String desc) {
        super("code:" + code + ",desc:" + desc);
        this.code = code;
        this.desc = desc;
    }

    public String toString() {
        String var10000 = super.toString();
        return var10000 + " code:" + this.code + " desc:" + this.desc;
    }
}
