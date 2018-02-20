package MultiThreads;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintNumContinously {

    public static final Lock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();
    public static int threadNum = 3;

    public PrintNumContinously(){

    }

    public static void main(String[] args){
        WipThreadId wipThreadId = new WipThreadId();
        wipThreadId.setWipThreadId(0);
        PrintNumContinously obj = new PrintNumContinously();
        for (int i = 0; i < threadNum; i ++){
            PrintThread t;
            if (i  == threadNum - 1){
                t = obj.new PrintThread(i, wipThreadId, 0);
            }else{
                t = obj.new PrintThread(i, wipThreadId, i + 1);
            }
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
            while (WipThreadId.count < 10){
                lock.lock();
                try{
                    while (wipThreadId.getWipThreadId() != threadId){
                        try{
                            condition.await();
                        }catch (Exception e){
                            System.out.println("got exception in awaiting condition.");
                        }
                    }
                    WipThreadId.increamentCnt();
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
        private static int count;

        public WipThreadId(){
        }

        public static void setCount(int cnt){
            count = cnt;
        }

        public static void increamentCnt(){
            count ++;
            System.out.println("Thread_" + wipThreadId + " print" + count);
        }

        public static void setWipThreadId(int threadId){
            wipThreadId = threadId;
        }

        public static int getWipThreadId(){
            return wipThreadId;
        }
    }

}
