package Tree.SegmentTree;

public class SegmentTree {
    /* Simple segment tree
               [0,  3]
             /        \
      [0,  1]           [2, 3]
      /     \           /     \
   [0, 0]  [1, 1]     [2, 2]  [3, 3]
     */
    public SimpleSegmentTreeNode build(int start, int end) {
        if (start > end){
            return null;
        }
        SimpleSegmentTreeNode node = new SimpleSegmentTreeNode(start, end);
        if (start == end){
            return node;
        }
        int mid = (start + end) / 2;
        SimpleSegmentTreeNode left = build(start, mid);
        SimpleSegmentTreeNode right = build(mid + 1, end);
        node.left = left;
        node.right = right;
        return node;
    }

    /* Segment Tree with max in range
                             [0,  3] (max = 4)
                          /            \
                [0,  1] (max = 3)     [2, 3]  (max = 4)
                /        \               /             \
        [0, 0](max = 3)  [1, 1](max = 2)[2, 2](max = 1) [3, 3] (max = 4)
     */
    public SegmentTreeNode build(int[] A) {
        return buildHelper(0, A.length - 1, A);
    }

    public SegmentTreeNode buildHelper(int start, int end, int[] A){
        if (A == null || A.length == 0){
            return null;
        }

        if (start == end){
            return new SegmentTreeNode(start, end, A[start]);
        }

        SegmentTreeNode node = new SegmentTreeNode(start, end, Integer.MIN_VALUE);
        int mid = (start + end) / 2;
        SegmentTreeNode left = buildHelper(start, mid, A);
        SegmentTreeNode right = buildHelper(mid + 1, end, A);
        node.left = left;
        node.right = right;
        if (node.left != null){
            node.max = Math.max(node.max, node.left.max);
        }
        if (node.right != null){
            node.max = Math.max(node.max, node.right.max);
        }
        return node;
    }

    public int query(SegmentTreeNode root, int start, int end) {
        if (start <= root.start && end >= root.end){
            return root.max;
        }

        int mid = (root.start + root.end) / 2;
        int max = Integer.MIN_VALUE;
        if (start <= mid){
            max = Math.max(max, query(root.left, start, end));
        }
        if (end >= mid + 1){
            max = Math.max(max, query(root.right, start, end));
        }
        return max;
    }
}
