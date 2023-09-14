package com.pingalax.common.factory.builderodeml;

/**
 * @author zhouxiaotao
 * @Description: 抽象接口类
 * @date 2023-09-13 11:33
 */
public interface ComputerBuilder {
    Computer computer = new Computer();

    void builderMotherboard();

    void builderCpu();

    void builderMemory();

    void builderDisk();

    void builderGpu();

    void builderPower();

    void builderHeatSink();

    void builderChassis();

    Computer build();
}
