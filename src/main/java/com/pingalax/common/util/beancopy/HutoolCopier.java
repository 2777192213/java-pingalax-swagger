package com.pingalax.common.util.beancopy;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.stereotype.Component;

/**
 * @author zhouxiaotao
 * @Description: 对象转换
 * @date 2023-09-19 14:05
 */
@Component
public class HutoolCopier {

    /**
     * bean 对象转换
     *
     * @param source 数据源对象
     * @param target 目标数据对象
     * @param <K> 数据源对象泛型
     * @param <T> 目标数据对象泛型
     * @return 转换后对象
     */
    public static  <K, T> T copy(K source, Class<T> target) throws Exception {
        return BeanUtil.toBean(source, target);
    }
}
