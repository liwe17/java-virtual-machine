package com.weiliai.direct;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;


/**
 * 演示直接内存溢出
 * java.lang.OutOfMemoryError: Direct buffer memory
 */
public class Demo1_10 {
    static int ALLOCATE_SIZE = 1024 * 1024 * 100;

    public static void main(String[] args) {
        List<ByteBuffer> list = new ArrayList<>();
        int i = 0;
        try {
            for(;;) {
                ByteBuffer byteBuffer = ByteBuffer.allocateDirect(ALLOCATE_SIZE);
                list.add(byteBuffer);
                i++;
            }
        } finally {
            System.out.println(i);
        }
        // 方法区是jvm规范:
        // jdk6 中对方法区的实现称为永久代
        // jdk8 对方法区的实现称为元空间
    }
}
