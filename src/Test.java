import java.util.Stack;

public class Test {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode deserialize(String data) {
        if (data == null){
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();
        boolean isLeftNull = false;
        TreeNode cur = new TreeNode(0);
        for (int i = 0; i < data.length(); i ++){
            switch(data.charAt(i)){
                case '<':
                    if (i + 1 < data.length() && data.charAt(i+1) == '>'){
                        isLeftNull = true;
                    }
                    stack.push(cur);
                    continue;
                case '>':
                    if (stack.size() > 1 && !isLeftNull){
                        stack.pop();
                    }
                    break;
                default:
                    int val = 0;
                    boolean isNeg = false;
                    if (data.charAt(i) == '-'){
                        isNeg = true;
                        i ++;
                    }
                    while (i < data.length() && Character.isDigit(data.charAt(i))){
                        val = val * 10 + Character.getNumericValue(data.charAt(i));
                        i ++;
                    }
                    i --;
                    val = isNeg ? -val : val;
                    cur = new TreeNode(val);
                    if (!stack.isEmpty()){
                        if (stack.peek().left == null && !isLeftNull){
                            stack.peek().left = cur;
                        }else{
                            stack.peek().right = cur;
                            isLeftNull = false;
                        }
                    }
            }
        }
        return stack.isEmpty() ? null : stack.pop();
    }

    public static void main(String[] args){
        Test t = new Test();
        t.deserialize("5<<2><3<<2<<3><1>>><4>>>>");
    }
}
