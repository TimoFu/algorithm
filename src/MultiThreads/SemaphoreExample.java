package MultiThreads;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    private static Semaphore sem;
    private static int count = 0;

    public static void main(String[] args){
        SemaphoreExample s = new SemaphoreExample();
        sem = new Semaphore(1, true);
        for (int i = 0; i < 3; i ++){
            CustomerThread t = s.new CustomerThread(i, sem);
            t.start();
        }
    }

    class CustomerThread extends Thread {
        private Semaphore sem;
        private int threadid;

        public CustomerThread(int threadid, Semaphore sem){
            this.sem = sem;
            this.threadid = threadid;
        }

        @Override
        public void run(){
            while (count < 30){
                try{
                    System.out.println("Thread_" + threadid + " is waiting for permit");
                    sem.acquire();
                    System.out.println("Thread_" + threadid + " received for permit");
                    for (int i = 0; i < 3; i ++){
                        System.out.println("Thread_" + threadid + " count " + (count ++));
                    }
                    sem.release();
                    Thread.sleep(10);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
