package com.pingalax.common.factory.factorymethod;

/**
 * @author zhouxiaotao
 * @Description: Tiramisu实现甜点类
 * @date 2023-09-13 9:15
 */
public class Tiramisu implements Dessert {
    @Override
    public Integer BuyMethod() {
        return 3;
    }

    @Override
    public String Mode() {
        return "Tiramisu";
    }
}
