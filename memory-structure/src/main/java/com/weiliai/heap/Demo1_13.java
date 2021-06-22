package com.weiliai.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Doug Li
 * @Date 2021/6/22
 * @Describe: 演示查看对象个数 堆转储 dump
 */
public class Demo1_13 {

    public static void main(String[] args) throws InterruptedException {
        List<Student> student = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            student.add(new Student());
        }
        TimeUnit.MINUTES.sleep(10);
    }
}

class Student {
    private byte[] big = new byte[1024 * 1024];
}
