package com.weiliai.stringtable;

/**
 * @Author: Doug Li
 * @Date 2021/6/25
 * @Describe: StringTable["a","b","ab"] hashtable结构,不能扩容
 */
public class Demo1_22 {
    //

    /**
     * 常量池中的信息,都会被加载到运行时常量池中,这时a b ab 都是常量池中的符号,还没有变为Java字符串对象
     * javap 后的关键代码
     *  0: ldc           #2                  // String a
     *  2: astore_1
     *  3: ldc           #3                  // String b
     *  5: astore_2
     *  6: ldc           #4                  // String ab
     *
     *  ldc #2 会把 a 符号变为"a"字符串对象
     */
    public static void main(String[] args) {
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        String s4 = s1+s2; // new StringBuilder().append("a").append("b").toString()  new String("ab")
        String s5 = "a"+"b"; //javac 在编译期间的优化,结果已经确定为ab

        System.out.println(s3==s5);
        System.out.println(s3==s4);

    }

}
