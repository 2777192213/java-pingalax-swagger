package com.pingalax.ext.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhouxiaotao
 * @Description: 哲学家进餐
 * @date 2023-10-09 8:41
 */
public class DiningPhilosophers {
    ReentrantLock[] forks = new ReentrantLock[5];

    public DiningPhilosophers() {
        for (int i = 0; i <= 4; i++) {
            forks[i] = new ReentrantLock();
        }
    }

    public void wantsToEat(int philosopher, Runnable pickLeftFork, Runnable pickRightFork, Runnable eat,
                           Runnable putLeftFork, Runnable putRightFork) throws InterruptedException {
        int fork1 = philosopher;
        int fork2 = (philosopher + 1) % 5;

        forks[Math.min(fork1, fork2)].lock();
        forks[Math.max(fork1, fork2)].lock();
        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();
        forks[Math.min(fork1, fork2)].unlock();

        forks[Math.max(fork1, fork2)].unlock();

    }


}
