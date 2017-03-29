package UnionFind;

import java.util.Arrays;

public class QuickUnion {
	
	public int[] ids;
	public int count;

	public QuickUnion() {
		// TODO Auto-generated constructor stub
	}
	
	public QuickUnion(int n){
		this.count = n;
		ids = new int[n];
		for (int i = 0; i < n; i ++){
			ids[i] = i;
		}
	}
	
	public int getCount(){
		return this.count;
	}
	
	public boolean connected(int p, int q){
		return root(p) == root(q);
	}
	
	public int root(int p){
		while (p != ids[p]){
			p = ids[p];
		}
		return p;
	}
	
	public void union(int p, int q){
		int proot = root(p);
		int qroot = root(q);
		if (proot == qroot){
			return;
		}
		// change its root to another root.
		ids[proot] = qroot;
		count --;
	}

	public static void main(String[] args) {
		QuickUnion union = new QuickUnion(10);
		union.union(4,3);
		System.out.println(Arrays.toString(union.ids));
		union.union(3,8);
		System.out.println(Arrays.toString(union.ids));
		union.union(6,5);
		System.out.println(Arrays.toString(union.ids));
		union.union(9,4);
		System.out.println(Arrays.toString(union.ids));
		union.union(2,1);
		System.out.println(Arrays.toString(union.ids));
		System.out.println(union.connected(8,9));
		System.out.println(union.connected(5,4));
		union.union(5,0);
		System.out.println(Arrays.toString(union.ids));
		union.union(7,2);
		System.out.println(Arrays.toString(union.ids));
		union.union(6,1);
		System.out.println(Arrays.toString(union.ids));
		System.out.println(union.getCount());

	}

}
