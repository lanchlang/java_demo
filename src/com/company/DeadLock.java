package com.company;

public class DeadLock {
    public static void main(String[] args){
         Object obj1=new Object();
         Object obj2=new Object();
         Thread t1=new Thread(new DeadLockRunnable(obj1,obj2));
         Thread t2=new Thread(new DeadLockRunnable(obj2,obj1));
         t1.start();
         t2.start();
    }
}
class DeadLockRunnable implements Runnable{
    private Object lock1;
    private Object lock2;

    public DeadLockRunnable(Object lock1, Object lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        synchronized (lock1){
            System.out.println("get lock1:"+lock1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2){
                System.out.println("get lock2:"+lock2);
            }
        }
    }
}