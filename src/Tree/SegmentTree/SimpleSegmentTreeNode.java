package Tree.SegmentTree;

public class SimpleSegmentTreeNode {
    public int start, end;
    public SimpleSegmentTreeNode left, right;

    public SimpleSegmentTreeNode(int start, int end){
        this.start = start;
        this.end = end;
        this.left = null;
        this.right = null;
    }
}
