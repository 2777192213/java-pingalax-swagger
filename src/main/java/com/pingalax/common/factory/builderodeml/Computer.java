package com.pingalax.common.factory.builderodeml;

import lombok.Data;

/**
 * @author zhouxiaotao
 * @Description: 电脑产品类
 * @date 2023-09-13 11:32
 */
@Data
public class Computer {
    private String motherboard;
    private String cpu;
    private String memory;
    private String disk;
    private String gpu;
    private String power;
    private String heatSink;
    private String chassis;
}
