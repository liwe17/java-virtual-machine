package com.weiliai.stack;

/**
 * @Author: Doug Li
 * @Date 2021/6/21
 * @Describe: 局部变量的线程安全问题
 */
public class Demo1_18 {

    /**
     * 不存在线程安全问题:
     * 每个线程都会产生新的栈帧即x都是独立的,线程私有的
     */
    static void m1(){
        int x = 0;
        for (int i = 0; i < 5000; i++) {
            x++;
        }
        System.out.println(x);
    }
}
