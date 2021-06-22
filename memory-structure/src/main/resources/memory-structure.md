# 内存结构
## 程序计数器
### 定义
- Program Counter Register 程序计数器(寄存器)
### 作用
- 作用:记录下一条JVM指令的执行地址
- 特点:
    - 线程私有
    - 不会存在内存溢出
 
## 虚拟机栈
### 定义
- Java Virtual Machine Stacks(虚拟机栈)
    - 每个线程运行时所需要的内存,称为虚拟机栈
    - 每个栈由多个栈帧(Frame)组成,对应着每个方法调用时所占用的内存
    - 每个线程只能有一个活动栈帧,对应当前正在执行的那个方法
    - 线程私有
    
- 问题辨析
    - 垃圾回收是否涉及内存?不涉及
    - 栈内存分配越大越好吗?
        - 默认为1M: -Xss size <=>  -XX:ThreadStackSize
    - 方法内的局部变量是否线程安全?
        - 如果方法局部变量没有逃离方法的作用访问,它是线程安全的
        - 如果局部变量引用了对象,并逃离方法的作用范围,需要考虑线程安全
        
- 代码实例:
    - com.weiliai.stack.Demo1_1
    - com.weiliai.stack.Demo1_17
    - com.weiliai.stack.Demo1_18
        
### 栈内存溢出
- 栈内存溢出
    - 栈帧过多导致栈内存溢出
    - 栈帧过大导致栈内存溢出

### 线程运行诊断

- 案例
    - CPU占用过多
        - com.weiliai.stack.Demo1_16
    - 程序运行很长无结果
        - com.weiliai.stack.Demo1_3
    
- CPU占用过高排查
    - Linux环境下执行(模拟现场): nohup java Demo1_16 2&>1 &
    - 定位
        - 使用top 查看CPU占用
        - ps H -eo pid,tid,%cpu |grep 进程id (用ps命令进一步定位是哪个线程引起的cpu占用过高)
        - printf "%x" tid 转换为16进制线程id
        - jstack 进程id |grep 16进制id
            - 根据线程id找到问题的线程,进一步定位到问题代码的源码行号
```text
[root@li ~]# nohup java Demo1_16 > nohup.out &
[1] 1276
[root@li ~]# top
top - 21:02:35 up 34 min,  2 users,  load average: 1.00, 0.99, 0.79
Tasks: 107 total,   1 running, 106 sleeping,   0 stopped,   0 zombie
%Cpu(s): 50.2 us,  0.0 sy,  0.0 ni, 49.8 id,  0.0 wa,  0.0 hi,  0.0 si,  0.0 st
KiB Mem :  1005652 total,   713260 free,   114648 used,   177744 buff/cache
KiB Swap:  2097148 total,  2097148 free,        0 used.   727292 avail Mem 

  PID USER      PR  NI    VIRT    RES    SHR S  %CPU %MEM     TIME+ COMMAND                                                                                                                  
 1319 root      20   0 2572168  29208  11372 S 100.0  2.9  17:19.03 java                                                                                                                     
  910 root      20   0  574032  17072   6012 S   0.3  1.7   0:00.46 tuned                                                                                                                    
    1 root      20   0  125304   3744   2560 S   0.0  0.4   0:01.18 systemd

[root@li ~]# ps H -eo pid,tid,%cpu |grep 1319
 1319  1319  0.0
 1319  1320  0.0
 1319  1321  0.0
 1319  1322  0.0
 1319  1323  0.0
 1319  1324  0.0
 1319  1325  0.0
 1319  1326  0.0
 1319  1327  0.0
 1319  1328  0.0
 1319  1329  100    --获取对应线程号
 1319  1330  0.0
 1319  1331  0.0

[root@li ~]# printf "%x" 1329
531
[root@li ~]# jstack 1319 |grep 531
"thread1" #8 prio=5 os_prio=0 tid=0x00007f80e81c5000 nid=0x531 runnable [0x00007f80c58ba000]
[root@li ~]# 
```
## 本地方法栈
- native方法
    - public native int hashCode();等等
- 线程私有
    
## 堆
### 定义
- Heap堆
    - 通过new关键字,创建的对象会使用堆内存
- 特点
    - 它是线程共享的,堆中对象都需要考虑线程安全问题
    - 有垃圾回收机制
### 堆内存溢出

- 代码
    - com.weiliai.heap.Demo1_5    
    
### 堆内存诊断

- jps工具
    - 查看当前系统中有那些Java进程
- jmap工具(某一个时刻)
    - jmap -heap pid
    - 查看堆内存占用情况
- jconsole工具(连续的时刻)
    - 图形界面的,多功能的检测工具,可以连续检测
    - com.weiliai.heap.Demo1_4
- jvisualvm工具(堆Dump)
    - 图形界面的,功能更多
    - com.weiliai.heap.Demo1_13

## 方法区
- Method Area(方法区)

### 组成

- 详见README.md

### 方法区内存溢出
- 1.8以前会导致永久代内存溢出
- 1.8之后会导致元空间内存溢出

- 代码
    - com.weiliai.metaspace.Demo1_8

- 场景
    - Spring
    - Mybatis

### 运行时常量池

- 常量池
    - 一张表,虚拟机指令根据这张表常量表找到要执行的类名,方法名,参数类型,字面量等信息
    - 二进制字节码
        - 类基本信息
        - 常量池
        - 类方法定义包含的虚拟机指令
        
- 运行时常量池
    - 常量池时*.class文件中的,当该类被加载,它的常量池信息就会放入运行时常量池,并把里面的符号地址变为真实地址
    



