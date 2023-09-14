package com.pingalax.common.util.baseresult;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author zhouxiaotao
 * @Description: 带参数的返回结果
 * @date 2023-08-08 14:27
 */
@Getter
@Setter
@ApiModel("返回结果")
@NoArgsConstructor
public class ResultData<T> {
    private T data;
    private String code;
    private String desc;
}
