package Tree.NArayTree;

import java.util.ArrayList;
import java.util.List;

public class LCA {

    List<Integer>[] tree = new List[1000];
    int[][] path = new int[3][1000];
    boolean flag;

    public LCA(){
        // empty constructor
    }

    private void addEdge(int a, int b){
        if (tree[a] == null){
            tree[a] = new ArrayList<>();
        }
        tree[a].add(b);
    }

    public int findLCA(int a, int b){
        if (a == b) return a;

        int aPath = 1, bPath = 2;
        path[aPath][0] = path[bPath][0] = 1;

        // find the root to node a
        flag = false;
        dfs(1, 0, aPath, 1, a);

        // find the root to node b
        flag = false;
        dfs(1, 0, bPath, 1, b);

        int i = 0;
        while (path[aPath][i] == path[bPath][i]){
            i ++;
        }

        return path[aPath][i-1];
    }

    private void dfs(int cur, int prev, int pathNum, int pathIndex, int node){
        // loop over all its children
        if (tree[cur] == null) return;
        for (int i = 0; i < tree[cur].size(); i ++){
            // it is not its previous node and the target node is not found yet.
            if (tree[cur].get(i) != prev && !flag){
                path[pathNum][pathIndex] = tree[cur].get(i);
                // if this one is the target node, we can stop finding
                if (tree[cur].get(i) == node){
                    flag = true;
                    path[pathNum][pathIndex + 1] = -1;
                    return;
                }
            }
            // if not, then recursive next node
            dfs(tree[cur].get(i), cur, pathNum, pathIndex + 1, node);
        }
    }

    public static void main(String[] args){
        LCA tree = new LCA();
        tree.addEdge(1,2);
        tree.addEdge(1,3);
        tree.addEdge(2,4);
        tree.addEdge(2,5);
        tree.addEdge(2,6);
        tree.addEdge(3,7);
        tree.addEdge(3,8);

        System.out.println("LCA of 3 and 4 is " + tree.findLCA(3, 4));
        System.out.println("LCA of 3 and 7 is " + tree.findLCA(3, 7));
        System.out.println("LCA of 4 and 6 is " + tree.findLCA(4, 6));
    }
}
