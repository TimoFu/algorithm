package MultiThreads.LockFree;

public class LockFreeLinkedList {

    public void insert(LockFreeStack.Node node, LockFreeStack.Node prev){
        while (true){
            node.next = prev.next;
            LockFreeStack.Node oldNext = prev.next;
            if (compare_and_swap(prev.next, oldNext, node) == oldNext)
                return;
        }
    }

    private LockFreeStack.Node compare_and_swap(LockFreeStack.Node curNext,
                                                LockFreeStack.Node oldNext,
                                                LockFreeStack.Node insertNode){
        return null;
    }
}
