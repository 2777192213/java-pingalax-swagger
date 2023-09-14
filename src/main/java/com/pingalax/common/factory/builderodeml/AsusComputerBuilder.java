package com.pingalax.common.factory.builderodeml;

import java.util.Set;

/**
 * @author zhouxiaotao
 * @Description: 具体 builder类
 * @date 2023-09-13 13:08
 */
public class AsusComputerBuilder implements ComputerBuilder{
    @Override
    public void builderMotherboard() {
        computer.setMotherboard("Motherboard主板");
    }

    @Override
    public void builderCpu() {
        computer.setCpu("Inter 12900KS\"");
    }

    @Override
    public void builderMemory() {
        computer.setMotherboard("芝奇幻峰戟 16G*2");
    }

    @Override
    public void builderDisk() {
        computer.setDisk("三星980Pro 2T");
    }

    @Override
    public void builderGpu() {
        computer.setGpu("华硕3090Ti 水猛禽");
    }

    @Override
    public void builderPower() {
        computer.setPower("雷神二代1200W");
    }

    @Override
    public void builderHeatSink() {
        computer.setHeatSink("龙神二代一体式水冷");
    }

    @Override
    public void builderChassis() {
        computer.setChassis("太阳神机箱");
    }

    @Override
    public Computer build() {
        return computer;
    }
}
