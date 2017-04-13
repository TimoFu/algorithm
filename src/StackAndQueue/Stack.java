package StackAndQueue;

public class Stack {
	
	private Node first = null;

	private class Node{
		String item;
		Node next;
		
		private Node(){
			
		}
	}
	
	public Stack() {
		// TODO Auto-generated constructor stub
	}

	public String pop(){
		if (first == null){
			return null;
		}
		
		String res = first.item;
		first = first.next;
		
		return res;
	}
	
	public void push(String item){
		Node oldFirst = first;
		Node node = new Node();
		node.item = item;
		node.next = oldFirst;
		first = node;
	}
	
	public static void main(String[] args){
		Stack stack = new Stack();
		stack.push("1");
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		stack.push("1");
		stack.push("2");
		stack.push("3");
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		stack.push("4");
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}
}
