package SegmentTree;

public class SegmentTreeNode {
    public int start, end, max;
    public SegmentTree.SegmentTreeNode left, right;

    public SegmentTreeNode(int start, int end, int max){
        this.start = start;
        this.end = end;
        this.max = max;
        this.left = null;
        this.right = null;
    }
}
