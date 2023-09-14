package com.pingalax.common.exceptions;

/**
 * @author zhouxiaotao
 * @Description: 异常抛出类
 * @date 2023-08-08 15:40
 */
public class ArgumentException extends RuntimeException {
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

    public ArgumentException(String code, String desc) {
        super("code:" + code + ",desc:" + desc);
        this.code = code;
        this.desc = desc;
    }

    public String toString() {
        String var10000 = super.toString();
        return var10000 + " code:" + this.code + " desc:" + this.desc;
    }
}
