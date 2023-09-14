package com.pingalax.common.factory.factorymethod.factory;

import com.pingalax.common.factory.factorymethod.Dessert;

/**
 * @author zhouxiaotao
 * @Description: 甜点工厂
 * @date 2023-09-13 9:19
 */
public class DessertFactory {
    /**
     * 使用反射创建对象
     * 加一个static变成静态工厂
     */
    public static Dessert create(Class<? extends Dessert> clazz) throws Exception {
        if (clazz != null) {
            return clazz.newInstance();
        }
        return null;
    }
}
