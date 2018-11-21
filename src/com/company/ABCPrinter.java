package com.company;
/**
 * 循环打印ABC：使用wait和nofity方法
 * */
public class ABCPrinter {
    public static void main(String[] args) throws InterruptedException {
          Object lockA=new Object();
          Object lockB=new Object();
          Object lockC=new Object();
          new Thread(new Printer(lockA,lockB,"A")).start();
          new Thread(new Printer(lockB,lockC,"B")).start();
          new Thread(new Printer(lockC,lockA,"C")).start();
//          Thread.sleep(100);
//          synchronized (lockA){
//              lockA.notify();
//          }
    }

}

class Printer implements Runnable{
    private final Object lock;
    private final Object notifier;
    private String content;
    Printer(Object lock,Object notifier,String content){
         this.lock=lock;
         this.notifier=notifier;
         this.content=content;
    }
    @Override
    public void run() {
        int i=0;
        while (i<10){
            synchronized (lock){
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
            }
                System.out.println(content+i);
                synchronized (notifier){
                    notifier.notify();
                }

            }
            i++;
        }
    }
}
