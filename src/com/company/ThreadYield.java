package com.company;
/**
 * Thread.sleep():不释放锁
 * Thread.yield():不释放锁
 * */
public class ThreadYield {

    public static void main(String[] args) throws InterruptedException {
        ThreadYield threadYield=new ThreadYield();
        new Thread(threadYield.new RunTime()).start();
        Thread.sleep(100);
        System.out.println("gggg");
        synchronized (threadYield){
            System.out.println("mmmmmm");
        }
    }

    class RunTime implements Runnable{
        @Override
        public void run() {
            synchronized (ThreadYield.this){
                while(true){
                    //System.out.println("kkkk");
                    Thread.yield();
//                    try {
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        }
    }
}
