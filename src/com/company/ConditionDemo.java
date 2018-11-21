package com.company;
/**
 * 循环打印ABC：使用ReentrantLock和Condition方法
 * */
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
    public static void main(String[] args) throws InterruptedException {
          ReentrantLock lock=new ReentrantLock();
          Condition conditionA=lock.newCondition();
          Condition conditionB=lock.newCondition();
          Condition conditionC=lock.newCondition();
          new Thread(new ConditionRunnable(conditionA,conditionB,lock,"A")).start();
          new Thread(new ConditionRunnable(conditionB,conditionC,lock,"B")).start();
          new Thread(new ConditionRunnable(conditionC,conditionA,lock,"C")).start();
          Thread.sleep(100);
          lock.lock();
          conditionA.signal();
          lock.unlock();
    }
}

class ConditionRunnable implements Runnable{
    private final Condition currentCondition;
    private final Condition nextCondition;
    private final ReentrantLock lock;
    private final String content;
    public ConditionRunnable(Condition currentCondition, Condition nextCondition, ReentrantLock lock,String content) {
        this.currentCondition = currentCondition;
        this.nextCondition = nextCondition;
        this.lock = lock;
        this.content=content;
    }

    @Override
    public void run() {
        int cnt=0;
        while(cnt<10){
            lock.lock();
            try{
                try {
                    currentCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(content+cnt);
                nextCondition.signal();

            }finally {
                lock.unlock();
            }
            cnt++;
        }
    }
}