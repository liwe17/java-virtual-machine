package com.weiliai.stack;

/**
 * @Author: Doug Li
 * @Date 2021/6/21
 * @Describe: 演示栈内存溢出 java.lang.StackOverflowError
 * -Xss256k
 */
public class Demo1_2 {

    private static int count;

    public static void main(String[] args) {
        try{
            method();
        }catch (Throwable ex){
            ex.printStackTrace();
            System.out.println(count); //打印执行多少次报错
        }
    }

    private static void method(){
        count++;
        method();
    }

}
