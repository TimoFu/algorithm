package MultiThreads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {

    private static BlockingQueue<String> buffer;
    private static int cnt;

    public static void main(String[] args){
        ProducerConsumer pc = new ProducerConsumer();
        buffer = new ArrayBlockingQueue<>(10);
        cnt = 0;

        for (int i = 0; i < 3; i ++){
            Producer p = pc.new Producer(buffer);
            p.start();
        }

        Consumer c = pc.new Consumer(buffer);
        c.start();

    }

    class Consumer extends Thread {
        private BlockingQueue<String> queue;

        public Consumer(BlockingQueue<String> queue){
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true){
                try {
                    String obj = queue.take();
                    Thread.sleep(1000);
                } catch (Exception e){
                    e.printStackTrace();
                }
                //System.out.println("Producer_" + producerId + " queue size " + queue.size());
            }
        }
    }

    class Producer extends Thread {
        private BlockingQueue<String> queue;

        public Producer(BlockingQueue<String> queue){
            this.queue = queue;
        }

        @Override
        public void run(){
            while (cnt < 30){
                try{
                    String obj = "Obj_" + (cnt++);
                    System.out.println("Add " + obj);
                    queue.put(obj);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
