package com.pingalax.common.factory.factorymethod;

/**
 * @author zhouxiaotao
 * @Description: Cheesecake 实现甜点类
 * @date 2023-09-13 9:18
 */
public class Cheesecake implements Dessert {
    @Override
    public Integer BuyMethod() {
        return 4;
    }

    @Override
    public String Mode() {
        return "Cheesecake";
    }
}
