package com.weiliai.recovery;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 演示GC Roots
 * jps 获取pid
 * jmap -dump:format=b,live,file=1.bin pid
 */
public class Demo2_2 {

    public static void main(String[] args) throws IOException {
        List<Object> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        System.out.println(1);
        System.in.read(); //用于jmap执行点

        list1 = null;
        System.out.println(2);
        System.in.read();
        System.out.println("end...");
    }
}
