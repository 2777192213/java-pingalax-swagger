package com.pingalax.common.util.baseresult;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhouxiaotao
 * @Description: 返回结果基础类
 * @date 2023-08-08 13:09
 */
@Data
@NoArgsConstructor //无参构造器
public class Result {
    private String code;
    private String desc;
}
