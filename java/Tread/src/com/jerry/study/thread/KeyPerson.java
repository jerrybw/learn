package com.jerry.study.thread;

/**
 * Created by jerry on 2018/3/5.
 */
public class KeyPerson extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始了战斗！");

        for (int i = 0;i<10;i++){
            System.out.println(Thread.currentThread().getName() + "左突右杀["+(i+1)+"]");
        }

        System.out.println(Thread.currentThread().getName()+"结束了战斗！");
    }
}
