package com.pingalax.common.util.beancopy;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author zhouxiaotao
 * @Description: 对象转换
 * @date 2023-09-19 13:54
 */
@Component
public class SpringBeanCopier {

    /**
     * 对象转换
     *
     * @param source 数据源对象
     * @param target 目标对象
     * @param <K>    数据源泛型
     * @param <T>    目标对象泛型
     * @return 返回
     * @throws IllegalAccessException 异常
     * @throws InstantiationException 异常
     */
    public static <K, T> T copy(K source, Class<T> target) throws IllegalAccessException, InstantiationException {
        T res = target.newInstance();
        BeanUtils.copyProperties(source, res);
        return res;
    }
}