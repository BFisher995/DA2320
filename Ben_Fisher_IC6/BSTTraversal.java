package Ben_Fisher_IC6;

import java.util.ArrayList;

public class BSTTraversal {
    public static ArrayList<Integer> res = new ArrayList<>();

    public static void inorderTraversal(TreeNode root){
        if(root != null) {
            inorderTraversal(root.left);
            res.add(root.val);
            inorderTraversal(root.right);
        }
    }

    public static void main(String[] args) {
        TreeNode d = new TreeNode(4);
        TreeNode f = new TreeNode(6);
        TreeNode g = new TreeNode(7);
        TreeNode e = new TreeNode(5, f, g);
        TreeNode b = new TreeNode(2, d, e);
        TreeNode j = new TreeNode(9);
        TreeNode h = new TreeNode(8, j, null);
        TreeNode c = new TreeNode(3, null, h);
        TreeNode r = new TreeNode(1, b, c);
        inorderTraversal(r);
        System.out.println(res);
    }
}
