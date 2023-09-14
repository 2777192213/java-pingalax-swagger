package com.pingalax.common.factory.abstractfactory;

import com.pingalax.common.factory.factorymethod.Coffee;
import com.pingalax.common.factory.factorymethod.Dessert;
import com.pingalax.common.factory.factorymethod.Mocha;
import com.pingalax.common.factory.factorymethod.Tiramisu;

/**
 * @author zhouxiaotao
 * @Description: 意式风格工厂
 * @date 2023-09-13 9:13
 */
public class ItalyFactory implements CoffeeShopFactory {
    @Override
    public Coffee createCoffee() {
        return new Mocha();
    }

    @Override
    public Dessert createDessert() {
        return new Tiramisu();
    }
}
