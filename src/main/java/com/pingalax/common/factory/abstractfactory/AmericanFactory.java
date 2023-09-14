package com.pingalax.common.factory.abstractfactory;

import com.pingalax.common.factory.factorymethod.Cappuccino;
import com.pingalax.common.factory.factorymethod.Cheesecake;
import com.pingalax.common.factory.factorymethod.Coffee;
import com.pingalax.common.factory.factorymethod.Dessert;

/**
 * @author zhouxiaotao
 * @Description: 美式风格工厂
 * @date 2023-09-13 9:12
 */
public class AmericanFactory implements CoffeeShopFactory {
    @Override
    public Coffee createCoffee() {
        return new Cappuccino();
    }

    @Override
    public Dessert createDessert() {
        return new Cheesecake();
    }
}
