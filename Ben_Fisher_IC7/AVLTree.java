package Ben_Fisher_IC7;

public class AVLTree {
    public class Node{

        public int val;
        public int height;
        public Node left;
        public Node right;

        public Node(){}
        public Node(int val){this.val = val;}
        public Node(int val, Node left, Node right){
            this.val = val;
            this.left = left;
            this.right = right;
        }

        public int height(){
            return height;
        }

        public void updateHeight(){

        }

        public int balanceFactor(){
            return 0;
        }
    }
}
