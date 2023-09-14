package com.pingalax.common.factory.builderodeml;

import lombok.AllArgsConstructor;

/**
 * @author zhouxiaotao
 * @Description: 指挥者类
 * @date 2023-09-13 13:58
 */
@AllArgsConstructor
public class ComputerDirector {
    private ComputerBuilder computerBuilder;
    public Computer construct() {
        computerBuilder.builderMotherboard();
        computerBuilder.builderGpu();
        computerBuilder.builderMemory();
        computerBuilder.builderDisk();
        computerBuilder.builderCpu();
        computerBuilder.builderHeatSink();
        computerBuilder.builderPower();
        computerBuilder.builderChassis();
        return computerBuilder.build();
    }

}
