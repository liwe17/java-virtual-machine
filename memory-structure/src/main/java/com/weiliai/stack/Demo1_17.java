package com.weiliai.stack;

/**
 * @Author: Doug Li
 * @Date 2021/6/21
 * @Describe: 局部变量的线程安全问题
 */
public class Demo1_17 {

    public static void main(String[] args) {

    }

    /**
     * sb是线程安全的,局部变量
     */
    public static void m1(){
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        sb.append(2);
        sb.append(3);
        System.out.println(sb.toString());
    }


    /**
     * sb是线程不安全的,通过参数传递,其他线程都可以得到修改
     */
    public static void m2(StringBuilder sb){
        sb.append(1);
        sb.append(2);
        sb.append(3);
        System.out.println(sb.toString());
    }


    /**
     * sb是线程不安全的,返回值可以北其他线程得到修改
     */
    public static StringBuilder m3(){
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        sb.append(2);
        sb.append(3);
        return sb;
    }
}
