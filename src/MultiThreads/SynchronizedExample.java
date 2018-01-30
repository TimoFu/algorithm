package MultiThreads;

import java.util.ArrayList;
import java.util.List;

public class SynchronizedExample {

    private static int cnt;
    private static List<String> buffer;

    public static void main(String[] args){
        SynchronizedExample obj = new SynchronizedExample();
        buffer = new ArrayList<>();
        cnt = 0;
        Thread p = new Thread(){
            @Override
            public void run(){
                ProducerConsumer p = obj.new ProducerConsumer(buffer);
                while (cnt < 30){
                    p.produce("obj_" + cnt);
                    try{
                        Thread.sleep(1);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                    cnt ++;
                }
            }
        };
        p.start();

        Thread c = new Thread(){
            @Override
            public void run(){
                ProducerConsumer c = obj.new ProducerConsumer(buffer);
                while (true){
                    c.consume();
                    try{
                        Thread.sleep(2);
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                }

            }
        };
        c.start();
    }

    class ProducerConsumer{
        private List<String> buffer;

        public ProducerConsumer(List<String> list){
            this.buffer = list;
        }

        public void produce(String item){
            synchronized (this){
                buffer.add("obj_" + cnt);
                System.out.println("Add item_" + cnt);
            }
        }

        public void consume(){
            synchronized (this){
                if (buffer.isEmpty()){
                    return;
                }
                String item = buffer.get(0);
                System.out.println("consume obj_" + item);
                buffer.remove(0);
            }
        }
    }
}
