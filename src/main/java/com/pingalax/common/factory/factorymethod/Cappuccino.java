package com.pingalax.common.factory.factorymethod;

import com.pingalax.common.factory.factorymethod.Coffee;

/**
 * @author zhouxiaotao
 * @Description: Cappuccino产品具体类，实现产品基类接口
 * @date 2023-09-12 15:53
 */
public class Cappuccino implements Coffee {
    @Override
    public Integer BuyMethod() {

        return 1;
    }

    @Override
    public String Mode() {
        return "Cappuccino";
    }
}
