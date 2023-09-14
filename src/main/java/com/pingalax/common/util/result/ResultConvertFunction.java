package com.pingalax.common.util.result;

/**
 * @author zhouxiaotao
 * @Description: 回调接口
 * @date 2023-08-08 10:06
 */
@FunctionalInterface
public interface ResultConvertFunction<T,E> {
    void convert(T a,E b);
}
