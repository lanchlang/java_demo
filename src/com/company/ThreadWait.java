package com.company;
/**
 *
 * wait():先获取锁，释放锁
 * notify():先获取锁，唤醒其中一个wait状态的线程
 * notifyAll():先获取锁，唤醒其中所有wait状态的线程
 * */
public class ThreadWait {
    public static void main(String[] args){
        ThreadWait tw=new ThreadWait();
        WaitRun waitRun=tw.new WaitRun();
        Thread t1=new Thread(waitRun);
        t1.start();
        WaitRun waitRun2=tw.new WaitRun();
        Thread t2=new Thread(waitRun2);
        t2.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (tw){
            System.out.println("t1 state is : "+t1.getState());
            System.out.println("t2 state is : "+t2.getState());
            //tw.notifyAll();
            tw.notify();
            System.out.println("t1 state is : "+t1.getState());
            System.out.println("t2 state is : "+t2.getState());
        }
        System.out.println("t1 state is : "+t1.getState());
        System.out.println("t2 state is : "+t2.getState());
        synchronized (tw){
            System.out.println("t1 state is : "+t1.getState());
            System.out.println("t2 state is : "+t2.getState());
            //tw.notifyAll();
            tw.notify();
            System.out.println("t1 state is : "+t1.getState());
            System.out.println("t2 state is : "+t2.getState());
        }
        System.out.println("t1 state is : "+t1.getState());
        System.out.println("t2 state is : "+t2.getState());
    }
    class WaitRun implements Runnable{
        @Override
        public void run() {
            try {
                synchronized (ThreadWait.this){
                    ThreadWait.this.wait();
                    System.out.println("go:"+this);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
