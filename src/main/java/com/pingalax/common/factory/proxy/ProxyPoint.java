package com.pingalax.common.factory.proxy;

import java.lang.reflect.Proxy;

/**
 * @author zhouxiaotao
 * @Description: 动态代理类
 * @date 2023-09-13 14:57
 */
public class ProxyPoint implements TrainStation {

    private TrainStation trainStation;

    private TrainStation getProxyObject(TrainStation trainStation) {
        this.trainStation = trainStation;
        Class<? extends TrainStation> clazz = trainStation.getClass();
        //return (TrainStation) Proxy.newProxyInstance(clazz.getClassLoader(),clazz.getInterfaces(),this);
        return null;
    }


    @Override
    public void sellTickets() {
        System.out.println("代理买票过程");
        trainStation.sellTickets();
    }
}
