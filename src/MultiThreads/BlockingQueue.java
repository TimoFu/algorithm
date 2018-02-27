package MultiThreads;

import java.util.LinkedList;
import java.util.Queue;

public class BlockingQueue {
    private Queue<Object> queue = new LinkedList<>();
    private int limit;

    public BlockingQueue(int size){
        this.limit = size;
    }

    public synchronized boolean add(Object obj) throws InterruptedException{
        while (isFull()){
            wait();
        }
        boolean isEmpty = isEmpty();
        queue.add(obj);
        if (isEmpty){
            notifyAll();
        }
        return true;
    }

    public synchronized Object poll() throws InterruptedException{
        while (queue.isEmpty()){
            wait();
        }
        boolean isFull = isFull();
        Object msg = queue.poll();
        if (isFull){
            notifyAll();
        }
        return msg;
    }

    private boolean isEmpty(){
        return queue.size() == 0;
    }

    private boolean isFull(){
        return queue.size() == limit;
    }
}
