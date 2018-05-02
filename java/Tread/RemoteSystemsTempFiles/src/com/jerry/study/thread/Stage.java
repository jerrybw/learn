package com.jerry.study.thread;

/**
 * Created by jerry on 2018/3/5.
 */
public class Stage extends Thread {

    public void run(){
        System.out.println("隋唐演义马上上映！请保持安静！");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }
        System.out.println("大幕徐徐拉开！");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArmyThread armyOfSuiDynasty = new ArmyThread();
        ArmyThread armyOfQiYi = new ArmyThread();

        Thread armyOfSuiDynastyThread = new Thread(armyOfSuiDynasty,"隋军");
        Thread armyOfQiYiThread = new Thread(armyOfQiYi,"农名起义军");

        armyOfSuiDynastyThread.start();
        armyOfQiYiThread.start();

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {

        }

        System.out.println("此时，半路杀出个程咬金");
        Thread MRChen = new KeyPerson();
        MRChen.setName("程咬金");

        System.out.println("双方军队同时鸣金收兵！");

        armyOfSuiDynasty.keepRunning = false;
        armyOfQiYi.keepRunning = false;

        MRChen.start();
        try {
            MRChen.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("演出到此结束！");
//
//        try {
//            armyOfQiYiThread.join();
//        } catch (InterruptedException e) {
//
//        }
    }


    public static void main(String[] args) {
        Thread stage = new Stage();
        stage.start();
    }
}
