package MultiThreads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintNumOneByOne {

    public static final Lock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    public PrintNumOneByOne(){

    }

    public static void main(String[] args){
        int threadNum = 30;
        WipThreadId wipThreadId = new WipThreadId();
        wipThreadId.setWipThreadId(0);
        PrintNumOneByOne obj = new PrintNumOneByOne();
        for (int i = 0; i < threadNum; i ++){
            PrintThread t = obj.new PrintThread(i, wipThreadId, i + 1);
            t.start();
        }
    }


    class PrintThread extends Thread {
        private int threadId, nextThreadId;
        private WipThreadId wipThreadId;

        public PrintThread(int threadId, WipThreadId wipThreadId, int nextThreadId){
            this.threadId = threadId;
            this.wipThreadId = wipThreadId;
            this.nextThreadId = nextThreadId;
        }

        @Override
        public void run(){
            while (true){
                lock.lock();
                try{
                    while (wipThreadId.getWipThreadId() != threadId){
                        try{
                            condition.await();
                        }catch (Exception e){
                            System.out.println("got exception in awaiting condition.");
                        }
                    }
                    System.out.println("Thread_" + threadId + " printing 1 2 3");
                    WipThreadId.setWipThreadId(nextThreadId);
                    condition.signalAll();
                }catch (Exception e){
                    System.out.println();
                }finally {
                    lock.unlock();
                }
            }
        }
    }

    public static class WipThreadId{
        private static int wipThreadId;

        public WipThreadId(){

        }

        public static void setWipThreadId(int threadId){
            wipThreadId = threadId;
        }

        public static int getWipThreadId(){
            return wipThreadId;
        }
    }

}
