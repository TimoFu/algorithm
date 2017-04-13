package UnionFind;

import java.util.Arrays;

public class WeightedQuickUnion {
	
	public int[] ids;
	public int[] size;
	public int count;

	public WeightedQuickUnion(int n) {
		// TODO Auto-generated constructor stub
		ids = new int[n];
		size = new int[n];
		for (int i = 0; i < n; i ++){
			ids[i] = i;
			size[i] = 1;
		}
			
		this.count = n;
	}
	
	public int getCount(){
		return this.count;
	}
	
	public int root(int p){
		while (p != ids[p]){
			// add one line to flat the tree
			ids[p] = ids[ids[p]];
			p = ids[p];
		}
		return p;
	}
	
	public boolean connected(int p, int q){
		return root(p) == root(q);
	}
	
	public void union(int p, int q){
		int proot = root(p);
		int qroot = root(q);
		if (proot == qroot){
			return;
		}
		if (size[proot] >= size[qroot]){
			ids[qroot] = proot;
			size[proot] += size[qroot];
		}else{
			ids[proot] = qroot;
			size[qroot] += size[proot];
		}
		
		count --;
	}

	public static void main(String[] args) {
		WeightedQuickUnion union = new WeightedQuickUnion(10);
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
