package com.pingalax.common.exceptions.handler;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhouxiaotao
 * @Description: 结果类
 * @date 2023-09-06 22:33
 */
@Data
@NoArgsConstructor
public class Result {
    private String code;
    private String desc;

    //public Result() {
    //}

    //public String getCode() {
    //    return this.code;
    //}
    //
    //public void setCode(String code) {
    //    this.code = code;
    //}
    //
    //public String getDesc() {
    //    return this.desc;
    //}
    //
    //public void setDesc(String desc) {
    //    this.desc = desc;
    //}
}
