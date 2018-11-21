package com.company;

public class ThreadJoin {
    public static void main(String[] args){
         Thread t1=new Thread(new Runnable() {
             @Override
             public void run() {
                 try {
                     System.out.println("t1 sleep start");
                     Thread.sleep(2000);
                     System.out.println("t1 sleep finish");
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         });
         Thread t2=new Thread(new Runnable() {
             @Override
             public void run() {
                 try {
                     System.out.println("t1.join start");
                     t1.join(1000);
                     System.out.println("t1.join end");
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 System.out.println("t2 task");
             }
         });
         t1.start();
         t2.start();
    }
}


