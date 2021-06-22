package com.weiliai.heap;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Doug Li
 * @Date 2021/6/22
 * @Describe: 演示堆内存
 */
public class Demo1_4 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("1...");
        TimeUnit.SECONDS.sleep(30);
        byte[] array = new byte[10 * 1024 * 1024]; //10M
        System.out.println("2...");
        TimeUnit.SECONDS.sleep(20);
        array=null;
        System.gc();
        System.out.println("3...");
        TimeUnit.SECONDS.sleep(1000);
    }
}
