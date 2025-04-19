package Ben_Fisher_HW4;

import java.util.*;

//@author Ben Fisher

public class BST {
    // Simple node class with a key, a left node, and a right node
    private class Node {
        int key;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    // Fields
    private Node root;

    // Constructors
    public BST(){root = null;}

    // Methods
    // This method places a key into the existing tree
    public void insert(int key){
        if (root == null) {
            root = new Node(key); // Inserts the new key as the root if the tree is empty
            return;
        }

        Node cur = root;
        // This loop traverses the tree until it finds a suitable location
        while (cur != null) {
            if (key < cur.key) {
                if (cur.left == null) {
                    cur.left = new Node(key);
                    return;
                }
                cur = cur.left;
            }
            else if (key > cur.key) {
                if (cur.right == null) {
                    cur.right = new Node(key);
                    return;
                }
                cur = cur.right;
            }
        }
    }

    // Determines whether or not a given key is in the tree
    public boolean search(int key){
        Node cur = root;
        while(cur != null){
            if(key == cur.key){
                return true;
            }
            else if(key < cur.key){
                cur = cur.left;
            }
            else if (key > cur.key){
                cur = cur.right;
            }
        }
        return false;
    }

    // Removes the node of the specified key from the tree
    public int delete(int key){
        Node parent = null;
        Node cur = root;

        // This loop finds the node to be deleted
        while(cur != null && cur.key != key){
            parent = cur;
            if(key < cur.key){
                cur = cur.left;
            }
            else if (key > cur.key){
                cur = cur.right;
            }
        }

        if (cur == null) {
            return -1; // Key does not exist in Tree
        }

        // If the node has only 0 or 1 subtrees, this executes and deletes the node while shifting the subtree up if needed
        if (cur.left == null || cur.right == null) {
            Node child;
            if (cur.left != null) {
                child = cur.left;
            }
            else {
                child = cur.right;
            }

            if (parent == null) {
                root = child;
            }
            else if (parent.left == cur) {
                parent.left = child;
            }
            else {
                parent.right = child;
            }
        }
        // This segment finds the in order successor (IOS) on the right subtree and then deletes and replaces the node
        else {
            Node IOSParent = cur;
            Node IOS = cur.right;

            while (IOS.left != null) {
                IOSParent = IOS;
                IOS = IOS.left;
            }

            cur.key = IOS.key;

            if (IOSParent.left == IOS) {
                IOSParent.left = IOS.right;
            }
            else {
                IOSParent.right = IOS.right;
            }
        }
        return key;
    }

    // Computes the maximum depth of the tree recursively
    public int depth(Node n){
        if (root == null) {
            return -1;
        }

        int leftDepth = depth(n.left);
        int rightDepth = depth(n.right);

        return 1 + Math.max(leftDepth, rightDepth);
    }

    // Finds the distance between 2 given nodes
    public int nodeDistance(int key1, int key2){
        if (!search(key1) || !search(key2)){ return -1;} // If one of the keys is not in the tree

        Node cur = root;

        // Finds lowest common ancestor
        while (cur != null) {
            if (key1 < cur.key && key2 < cur.key) {
                cur = cur.left;
            }
            else if (key1 > cur.key && key2 > cur.key) {
                cur = cur.right;
            }
            else {
                break;
            }
        }

        //Uses get distance to find the number of edges between the nodes
        return getDistance(cur, key1) + getDistance(cur, key2);
    }

    // Helper method for nodeDistance that finds the distance between a known ancestor and a specified key
    private int getDistance(Node root, int key) {
        int distance = 0;
        Node cur = root;

        while (cur != null) {
            if (key == cur.key) {
                return distance;
            } else if (key < cur.key) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
            distance++;
        }
        return distance;
    }

    // Breadth-First printing of the tree
    public void print(){
        if (root == null) {
            return; // Prints nothing if empty
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.remove();
            System.out.print(current.key + " ");

            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }
        System.out.println();
    }
}
