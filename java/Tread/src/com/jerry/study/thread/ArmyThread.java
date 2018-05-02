package com.jerry.study.thread;

import java.security.PublicKey;

/**
 * Created by jerry on 2018/3/5.
 */
public class ArmyThread implements Runnable{

    volatile boolean keepRunning = true;

    @Override
    public void run() {


        while (keepRunning) {

            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "发起攻击[" + (i + 1) + "]");
                Thread.yield();
            }
        }

        System.out.println(Thread.currentThread().getName() + "停止战斗！");
    }
}
