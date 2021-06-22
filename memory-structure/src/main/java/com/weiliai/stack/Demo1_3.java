package com.weiliai.stack;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Doug Li
 * @Date 2021/6/22
 * @Describe: 程序长时间运行无结果
 */
public class Demo1_3 {

    static A a = new A();
    static B b = new B();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (a){
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (b){
                System.out.println("我获得了a和b");
            }
        }).start();

        new Thread(()->{
            synchronized (b){
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            synchronized (a){
                System.out.println("我获得了a和b");
            }
        }).start();
    }

}

class A{}
class B{}
