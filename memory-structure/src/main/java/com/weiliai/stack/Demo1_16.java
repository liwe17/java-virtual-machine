package com.weiliai.stack;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Doug Li
 * @Date 2021/6/22
 * @Describe: CPU占用过高
 *  测试过程:
 *  1.在linux环境下运行,需要删除package否则需要配置classpath
 *  2.nohup java Demo1_16 2&>1 &
 *  定位:
 *  1.top 查看CPU占用
 *  2.ps H -eo pid,tid,%cpu |grep 进程id (用ps命令进一步定位是哪个线程引起的cpu占用过高)
 *  3.printf "%x" tid
 *  3.jstack 进程id
 */
public class Demo1_16 {

    public static void main(String[] args) {
        new Thread(null, () -> {
            System.out.println("1....");
            for (; ; ) {
            }
        }, "thread1").start();


        new Thread(null, () -> {
            try {
                System.out.println("2....");
                TimeUnit.SECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread2").start();

        new Thread(null, () -> {
            try {
                System.out.println("3....");
                TimeUnit.SECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread3").start();
    }

}
