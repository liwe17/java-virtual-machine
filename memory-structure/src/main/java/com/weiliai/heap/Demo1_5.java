package com.weiliai.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Doug Li
 * @Date 2021/6/22
 * @Describe: 演示堆内存溢出 java.lang.OutOfMemoryError: Java heap space
 * -Xms8m
 */
public class Demo1_5 {

    public static void main(String[] args) {
        int i = 0;
        try{
            List<String> list = new ArrayList<>();
            String a = "hello world";
            for(;;){
                list.add(a);
                a = a + a;
                i++;
            }
        }catch (Throwable e){
            e.printStackTrace();
            System.out.println(i);
        }

    }
}
