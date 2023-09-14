package com.pingalax.common.factory.factorymethod.factory;

import com.pingalax.common.factory.factorymethod.Coffee;

/**
 * @author zhouxiaotao
 * @Description: 工厂类
 * @date 2023-09-12 15:50
 */
public class CoffeeFactory {
    /**
     * 使用反射创建对象
     * 加一个static变成静态工厂
     */
    public static Coffee create(Class<? extends Coffee> clazz) throws Exception {
        if (clazz != null) {
            return clazz.newInstance();
        }
        return null;
    }
}
