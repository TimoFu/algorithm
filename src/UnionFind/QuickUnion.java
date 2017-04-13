package UnionFind;

public class QuickUnion {

	public int[] ids;
	public int count;

	public QuickUnion() {
		// TODO Auto-generated constructor stub
	}

	public QuickUnion(int n) {
		ids = new int[n];
		for (int i = 0; i < n; i++) {
			ids[i] = n;
		}
		count = n;
	}

	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}

	public void union(int p, int q) {
		int proot = ids[p];
		int qroot = ids[q];
		if (proot == qroot) {
			return;
		}
		ids[p] = ids[q];
		count--;
	}

	public int root(int p) {
		while (p != ids[p]) {
			p = ids[p];
		}
		return p;
	}

	public int getCount() {
		return count;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
