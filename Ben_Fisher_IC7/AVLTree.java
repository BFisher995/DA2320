package Ben_Fisher_IC7;

import java.util.ArrayList;

public class AVLTree {
    public class Node{

        public int val;
        public int height;
        public Node left;
        public Node right;

        public Node(){height = 0;}
        public Node(int val){this.val = val; height = 0;}
        public Node(int val, Node left, Node right){
            this.val = val;
            this.left = left;
            this.right = right;
            height = 0;
        }

        public int getHeight(){
            return height;
        }

        public int balanceFactor(){
            return this.right.height - this.left.height;
        }
    }

    public static ArrayList<Integer> res = new ArrayList<>();
    public static void inorderTraversal(Node root){ //copied from IC5
        if(root != null) {
            inorderTraversal(root.left);
            res.add(root.val);
            inorderTraversal(root.right);
        }
    }

    public int calculateHeight(Node n){ //assigns all height values in the tree starting from root n, functions as updateHeight
        if(n.left == null && n.right == null){
            n.height = 0;
            return 0;
        }
        else if (n.right == null){
            n.height = 1 + calculateHeight(n.left);
            return 1 + calculateHeight(n.left);
        }
        else if (n.left == null){
            n.height = calculateHeight(n.right);
            return 1 + calculateHeight(n.right);
        }
        n.height = Math.max(1+calculateHeight(n.left), 1+calculateHeight(n.right));
        return n.height;
    }

    public void leftRotation(Node m){ //Right-Right rotation
        Node n = m.right;

        m.right = null;
        if(n.left != null){
            m.right = n.left;
            n.left = m;
        }
        else{
            n.left = m;
        }

        calculateHeight(n);
    }

    public void rightRotation(Node m){ //Left-Left rotation
        Node n = m.left;

        m.right = null;
        if(n.right != null){
            m.left = n.right;
            n.right = m;
        }
        else{
            n.right = m;
        }

        calculateHeight(n);
    }

    public void rebalance(Node root, Node n){
        if(n.balanceFactor() < -1){
            rightRotation(n);
        }
        else if(n.balanceFactor() > 1){
            leftRotation(n);
        }
        res.clear();
        inorderTraversal(root);
        for(int item : res){
            System.out.print(item + ", ");
        }
        System.out.println();
    }
}
