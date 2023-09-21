package com.pingalax.common.thread;

/**
 * @author zhouxiaotao
 * @Description: 基于ThreadLocal封装工具类
 * @date 2023-09-18 9:46
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
}
