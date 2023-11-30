package com.pingalax.ext.task;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author zhouxiaotao
 * @Description: 时间轮定时任务
 * @date 2023-10-09 12:59
 */
@Api(value = "时间轮定时任务相关", tags = "时间轮定时任务相关")
@RestController
@RequestMapping("/TimerTask")
@Slf4j
public class HashedWheelTimerTask {
    /**
     * public HashedWheelTimer(
     * ThreadFactory:threadFactory,
     * long tickDuration, TimeUnit unit, int ticksPerWheel, boolean leakDetection,
     * long maxPendingTimeouts, Executor taskExecutor) {
     * <p>
     * }
     * ThreadFactory 线程工厂 默认时Executors.defaultThreadFactory()
     * tickDuration：tick（格子）的周期
     * unit：tick周期单位
     * ticksPerWheel：时间轮的长度，一圈下来一共多少格
     */
    @ApiOperation("5秒后执行TimerTask")
    @PostMapping("/simpleHashedWheelTimer")
    public void simpleHashedWheelTimer() {
        log.info("init task 1...");

        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1, TimeUnit.SECONDS, 8);

        hashedWheelTimer.newTimeout(timeout -> {
            log.info("running task 1...");
        }, 5, TimeUnit.SECONDS);
    }

    @ApiOperation("任务失效后cancel并让它重新在3秒后执行")
    @SneakyThrows
    @PostMapping("/ reScheduleHashedWheelTimer")
    public void reScheduleHashedWheelTimer() {
        log.info("init task 2...");
        HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1, TimeUnit.SECONDS, 8);
        Thread.sleep(5000);
        Timeout newTimeout = hashedWheelTimer.newTimeout(timeout -> {
            log.info("running task 2...");

        }, 3, TimeUnit.SECONDS);

        //newTimeout.isExpired()是否过期
        if (!newTimeout.isExpired()) {
            log.info("cancel task 2...");
            newTimeout.cancel();
        }

        hashedWheelTimer.newTimeout(newTimeout.task(), 3, TimeUnit.SECONDS);
    }

}
