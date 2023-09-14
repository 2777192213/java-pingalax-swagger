package com.pingalax.common.factory.factorymethod;

import com.pingalax.common.factory.factorymethod.Coffee;

/**
 * @author zhouxiaotao
 * @Description: Mocha品牌咖啡
 * @date 2023-09-12 16:27
 */
public class Mocha implements Coffee {
    @Override
    public Integer BuyMethod() {
        return 2;
    }

    @Override
    public String Mode() {
        return "Mocha";
    }
}
