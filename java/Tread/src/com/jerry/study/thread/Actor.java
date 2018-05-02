package com.jerry.study.thread;

/**
 * Created by jerry on 2018/3/5.
 */
public class Actor extends Thread{

    @Override
    public void run() {
        System.out.println(getName() + "开始表演！");

        int count = 0;

        while (count++ < 100){
            System.out.println(getName() + "表演了：" + count + "次");

            if(count % 10 == 0){
                System.out.println(getName() + "休息一下");
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {

                }
            }
        }

        System.out.println(getName() + "结束了表演！");
    }

    public static void main(String[] args) {
        Thread actor = new Actor();
        actor.setName("MR.Actor");

        Thread actress = new Thread(new Actress(),"MRS.Actress");
        actor.start();
        actress.start();
    }

}

class Actress implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始表演！");

        int count = 0;

        while (count++ < 100){
            System.out.println(Thread.currentThread().getName() + "表演了：" + count + "次");

            if(count % 10 == 0){
                System.out.println(Thread.currentThread().getName() + "休息一下");
                try {
                    Thread.sleep(1000l);
                } catch (InterruptedException e) {

                }
            }

        }

        System.out.println(Thread.currentThread().getName() + "结束了表演！");
    }
}