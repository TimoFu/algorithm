package MultiThreads.LockFree;

import javax.xml.soap.Node;
import java.util.Stack;

public class LockFreeStack {

    /**
     * Use CAS to check the top of the stack is not changed in between creating
     * the new top and writing it to memory;
     */
    public void push(Stack<Node> s, Node node){
        while (true){
            Node oldTop = s.peek();
            node.next = oldTop;
            if (compare_and_swap(s.peek(), oldTop, node) == oldTop) {
                return;
            }
        }
    }

    public Node pop(Stack<Node> stack){
        while (true){
            Node top = stack.peek();
            int popCnt = stack.popCount;
            if (top == null) return null;
            Node newTop = top.next;
            if (double_compare_and_swap(stack.peek(), top, newTop,
                                        stack.popCount, popCnt, popCnt + 1))
                return top;
        }
    }

    class Node{
        public int val;
        public Node next;

        public Node(){}
    }

    class Stack<Node>{
        public int popCount;

        public void push(){

        }

        public Node peek(){
            return null;
        }

    }

    private Node compare_and_swap(Node curTop, Node oldTop, Node node){
        return new Node();
    }

    private boolean double_compare_and_swap(Node cur, Node old, Node next,
                                            int curPopCnt, int oldPopCnt, int updatedPopCnt){
        return true;
    }
}
