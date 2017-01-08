package com.zdcf.test;

import java.io.*;
//多线程编程
public class MultiThread
{
    public static void main(String args[])
    {
        System.out.println("我是主线程!");
//下面创建线程实例thread1
        ThreadUseExtends thread1 = new ThreadUseExtends();
//创建thread2时以实现了Runnable接口的THhreadUseRunnable类实例为参数
        Thread thread2 = new Thread(new ThreadUseRunnable(), "SecondThread");
        thread1.start();//启动线程thread1使之处于就绪状态
//thread1.setPriority(6);//设置thread1的优先级为6
//优先级将决定cpu空出时，处于就绪状态的线程谁先占领cpu开始运行
//优先级范围1到10,MIN_PRIORITY,MAX_PRIORITY,NORM_PAIORITY
//新线程继承创建她的父线程优先级,父线程通常有普通优先级即5NORM_PRIORITY
        System.out.println("主线程将挂起7秒!");
        try
        {
            Thread.sleep(7000);//主线程挂起7秒
        }
        catch (InterruptedException e)
        {
            return;
        }
        System.out.println("又回到了主线程!");
        if (thread1.isAlive())
        {
            thread1.stop();//如果thread1还存在则杀掉他
            System.out.println("thread1休眠过长,主线程杀掉了thread1!");
        }
        else
            System.out.println("主线程没发现thread1,thread1已醒顺序执行结束了!");
        thread2.start();//启动thread2
        System.out.println("主线程又将挂起7秒!");
        try
        {
            Thread.sleep(7000);//主线程挂起7秒
        }
        catch (InterruptedException e)
        {
            return;
        }
        System.out.println("又回到了主线程!");
        if (thread2.isAlive())
        {
            thread2.stop();//如果thread2还存在则杀掉他
            System.out.println("thread2休眠过长，主线程杀掉了thread2!");
        }

        else
            System.out.println("主线程没发现thread2,thread2已醒顺序执行结束了!");
        System.out.println("程序结束按任意键继续!");
        try
        {
            System.in.read();
        }
        catch (IOException e)
        {
            System.out.println(e.toString());
        }
    }//main
}//MultiThread
class ThreadUseExtends extends Thread
//通过继承Thread类,并实现它的抽象方法run()
//适当时候创建这一Thread子类的实例来实现多线程机制
//一个线程启动后(也即进入就绪状态)一旦获得CPU将自动调用它的run()方法
{
    ThreadUseExtends() {} //构造函数
    public void run()
    {
        System.out.println("我是Thread子类的线程实例!");
        System.out.println("我将挂起10秒!");
        System.out.println("回到主线程,请稍等,刚才主线程挂起可能还没醒过来!");
        try
        {
            sleep(10000);//挂起5秒
        }
        catch (InterruptedException e)
        {
            return;
        }
//如果该run()方法顺序执行完了,线程将自动结束,而不会被主线程杀掉
//但如果休眠时间过长,则线程还存活,可能被stop()杀掉
    }
}
class ThreadUseRunnable implements Runnable
//通过实现Runnable接口中的run()方法,再以这个实现了run()方法的类
//为参数创建Thread的线程实例
{
//Thread thread2=new Thread(this);
//以这个实现了Runnable接口中run()方法的类为参数创建Thread类的线程实例
    ThreadUseRunnable() {} //构造函数
    public void run()
    {
        System.out.println("我是Thread类的线程实例并以实现了Runnable接口的类为参数!");
        System.out.println("我将挂起1秒!");
        System.out.println("回到主线程,请稍等,刚才主线程挂起可能还没醒过来!");
        try
        {
            Thread.sleep(1000);//挂起5秒
        }
        catch (InterruptedException e)
        {
            return;
        }
//如果该run()方法顺序执行完了,线程将自动结束,而不会被主线程杀掉
//但如果休眠时间过长,则线程还存活,可能被stop()杀掉
    }
}
//该程序可做的修改如改休眠时间或优先级setPriority()