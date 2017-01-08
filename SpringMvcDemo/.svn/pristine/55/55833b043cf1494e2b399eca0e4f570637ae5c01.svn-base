package com.zdcf.test;

public class RunnableTicket
{
    public static void main(String[] args)
    {
        MyThread mt = new MyThread();
//        new Thread(mt).start();//同一个mt，但是在Thread中就不可以，如果用同一
//        new Thread(mt).start();//个实例化对象mt，就会出现异常
        
        for(int i=0;i<100;i++){
        	new Thread(mt).start();
        }
        
//        new Thread(mt).start();
//        new Thread(mt).start();
//        new Thread(mt).start();
//        new Thread(mt).start();
//        new Thread(mt).start();
        
        
    }
}
class MyThread implements Runnable
{
    private int ticket = 10;
    public void run()
    {
        for (int i = 0; i < 20; i++)
        {
            if (this.ticket > 0)
            {
                System.out.println("卖票：ticket" + this.ticket--);
            }
        }
    }
}
