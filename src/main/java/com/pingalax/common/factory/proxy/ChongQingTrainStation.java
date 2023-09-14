package com.pingalax.common.factory.proxy;

/**
 * @author zhouxiaotao
 * @Description: 重庆火车站
 * @date 2023-09-13 14:59
 */
public class ChongQingTrainStation implements TrainStation{
    @Override
    public void sellTickets() {
        System.out.println("cq火车站开始买票了！");
    }
}
