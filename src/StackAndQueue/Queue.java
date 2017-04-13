package StackAndQueue;

public class Queue {
	
	private Node first;
	private Node last;
	
	private class Node{
		String item;
		Node next;
		
		private Node(){
			
		}
	}

	public Queue() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean isEmpty(){
		return first == null;
	}
	
	public String dequeue(){
		if (isEmpty()){
			first = last;
			return null;
		}
		Node oldFirst = first;
		first = first.next;
		return oldFirst.item;
	}
	
	public void enqueue(String item){
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		
		if (isEmpty()){
			first = last;
		}else{
			oldLast.next = last;
		}
	}
	
	public static void main(String[] args){
		Queue queue = new Queue();
		System.out.println(queue.dequeue());
		queue.enqueue("1");
		System.out.println(queue.dequeue());
		queue.enqueue("1");
		queue.enqueue("2");
		System.out.println(queue.dequeue());
		queue.enqueue("3");
		queue.enqueue("4");
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
	}

}
