package com.pingalax.common.factory.builderodeml;

/**
 * @author zhouxiaotao
 * @Description: 测试类
 * @date 2023-09-13 14:01
 */
public class main {
    public static void main(String[] args) {
        ComputerDirector computerDirector = new ComputerDirector(new AsusComputerBuilder());
        System.out.println(computerDirector.construct());
    }
}
