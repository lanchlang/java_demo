package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {
         System.out.println("thread start");
	     Thread t=new Thread(new Task("test"));
        t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                e.printStackTrace();
            }
        });
	     try{
             t.start();
             //t.start();
         }catch (Exception e){
	         e.printStackTrace();
         }
         Thread.sleep(200);
	     System.out.println("thread t is interrupted : "+t.isInterrupted());
	     System.out.println("thread t is alive : "+t.isAlive());
	     System.out.println("thread t is isDaemon : "+t.isDaemon());
	     System.out.println("thread t state is : "+t.getState());
	     t.interrupt();

        System.out.println("thread t state is : "+t.getState());
        Thread.sleep(200);
        System.out.println("thread t state is : "+t.getState());
        System.out.println("thread t is interrupted : "+t.isInterrupted());
        System.out.println("thread t is alive : "+t.isAlive());
        System.out.println("thread t is isDaemon : "+t.isDaemon());
	    System.out.println("main thread end");
	    t.start();
	     //Thread.sleep(1000);
    }
}

class Task implements Runnable{
    private String name;
    Task(String name) {
        this.name=name;
    }

    @Override
    public void run() {
        System.out.println("task is : "+this.name);
        int a=0;
        int b=0;
        System.out.println("task finish");
        int c=b/a;
    }
}

