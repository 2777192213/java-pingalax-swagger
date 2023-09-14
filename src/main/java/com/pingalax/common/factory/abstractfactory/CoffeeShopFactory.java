package com.pingalax.common.factory.abstractfactory;


import com.pingalax.common.factory.factorymethod.Coffee;
import com.pingalax.common.factory.factorymethod.Dessert;

/**
 * @author zhouxiaotao
 * @Description: 抽象工厂
 * @date 2023-09-12 17:02
 */
public interface CoffeeShopFactory {
    // * 咖啡类
    Coffee createCoffee();

    //甜点类
    Dessert createDessert();
}
