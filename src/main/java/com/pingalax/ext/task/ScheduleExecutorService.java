package com.pingalax.ext.task;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhouxiaotao
 * @Description: ScheduleExecutorService实现方式
 * @date 2023-10-09 10:12
 */
@Api(value = "ScheduleExecutorService实现方式", tags = "ScheduleExecutorService实现方式")
@RestController
@Slf4j
@RequestMapping("/ScheduleExecutorService")
public class ScheduleExecutorService {

    /**
     * 使用ScheduledExecutorService实现定时任务取代Timer的原因：
     * ScheduledExecutorService是基于线程池的，可以开启多个线程进行执行多个任务，每个任务开启一个线程；
     * 这样任务的延迟和未处理异常就不会影响其它任务的执行了。
     */

    @ApiOperation("schedule延时执行一个任务")
    @PostMapping("/DeferredMandatesTask")
    @SneakyThrows
    public void scheduleOneTask() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                log.info("ScheduleExecutorService-task @{}", LocalDateTime.now());
            }
        }, 1000, TimeUnit.MILLISECONDS);

        Thread.sleep(10000);
        executorService.shutdown();
    }



    @ApiOperation("scheduleAtFixedRate周期任务")
    @PostMapping("/scheduleAtFixedRateTask")
    @SneakyThrows
    public void scheduleAtFixedRateTask() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            @SneakyThrows
            public void run() {
                if (atomicInteger.getAndIncrement() == 2) {
                    Thread.sleep(5000);
                }
                log.info("ScheduleExecutorService-task-{} @{}", atomicInteger.getAndIncrement(), LocalDateTime.now());
            }
        }, 500, 1000, TimeUnit.MILLISECONDS);

        Thread.sleep(10000);
        executorService.shutdown();
    }


    /**
     * scheduleAtFixedDelay: 每次执行完当前任务后，然后间隔一个Period的时间在执行一个任务；当某个任务执行周期大于时间间隔时
     * 依然按间隔时间执行下一个任务，即它保证了任务之间执行的间隔。
     */
    @ApiOperation("scheduleWithFixedDelay周期任务")
    @PostMapping("/scheduleWithFixedDelayTask")
    @SneakyThrows
    public void scheduleWithFixedDelayTask() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            @SneakyThrows
            public void run() {
                if (atomicInteger.getAndIncrement() == 2) {
                    Thread.sleep(5000);
                }
                log.info("ScheduleExecutorService-task-{} @{}", atomicInteger.getAndIncrement(), LocalDateTime.now());
            }
        }, 500, 1000, TimeUnit.MILLISECONDS);

        Thread.sleep(10000);
        executorService.shutdown();
    }

}
