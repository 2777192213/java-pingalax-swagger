package com.pingalax.ext.task;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author zhouxiaotao
 * @Description: Timer实现定时任务
 * @date 2023-10-08 14:00
 */
@RestController
@Slf4j
@Api(value = " Timer实现方式", tags = "Timer实现方式")
@RequestMapping("task")
public class TimedTask {

    /**
     * Timer底层使用的一个单线程实现Timer任务处理。所有任务都是同一个线程来调度，所有任务都是串行执行
     */
    @ApiOperation("延时任务")
    @PostMapping("/DeferredMandatesTask")
    @SneakyThrows
    public void DeferredMandatesTask() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("timer-task @{}", LocalDateTime.now());
            }
        }, 1000);
        long startTime = System.currentTimeMillis();
        Thread.sleep(3000);
        long endTime = System.currentTimeMillis();
        long durationInMillis = endTime - startTime;
        log.info("{}", durationInMillis);
        timer.cancel();
    }

    @ApiOperation("周期任务")
    @PostMapping("/scheduleTask")
    @SneakyThrows
    public void scheduleTask() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            @SneakyThrows
            public void run() {
                log.info("timer-task @{}", LocalDateTime.now());
                Thread.sleep(100);
            }
        }, 500, 1000);
        Thread.sleep(10000);
        timer.cancel();
    }


}
