package Ben_Fisher_HW4;

import java.util.*;
/**
* This class implements an AVL tree containing key->value pairs.
* AVL trees are self-balancing binary search trees.
*
* @author Ben Fisher
*
* @param <K> Object type for keys
* @param <V> Object type for values
*/
public class AVLTree<K extends Comparable<K>, V> {
    private AVLNode<K,V> root;

    /**
    * Constructor creates an empty AVLTree.
    */
    public AVLTree() {
        root = null;
    }

    /**
    * This method inserts a given key->value pair into the tree.
    * If key already exists, overrides old value with new value.
    * @param key the key to be inserted
    * @param value the value to be inserted
    */
    public void insert(K key, V value) {
        root = insert(root, key, value);
    }

    /**
    * This (recursive) helper method inserts a given key->value pair into a
    given tree.
    * If key already exists, overrides old value with new value.
    * @param n the root of the tree
    * @param key the key to be inserted
    * @param value the value to be inserted
    * @return the root of the new tree
    */
    private AVLNode<K,V> insert(AVLNode<K,V> n, K key, V value) {
        // Base case: if n is empty, replace it with a new node containing key->value
        if (n == null) {return new AVLNode<K,V>(key, value);}
        // Base case: if key is already in the tree, replace its value with value
        if (n.getKey().equals(key)) {
            n.setValue(value);
            return n;
        }
        // Recursive cases: insert into appropriate sub-tree, balance, and return
        if (key.compareTo(n.getKey()) < 0){n.setLeft(insert(n.getLeft(), key, value));}
        else {n.setRight(insert(n.getRight(), key, value));}
            n.setHeight(1 + Math.max(height(n.getLeft()), height(n.getRight())));
            return balance(n);
    }

    /**
    * This method retrieves the data corresponding to a given key.
    * If the key does not exist, returns null.
    * @param key the key of the data to be retrieved
    * @return data corresponding to key
    */
    public V lookup(K key) {
        return lookup(root, key);
    }

    /**
    * This (recursive) helper method retrieves the data corresponding to a given
    key from a given tree.
    * @param n the root of the tree
    * @param key the key of the data to be retrieved
    * @return data corresponding to key
    */
    private V lookup(AVLNode<K,V> n, K key) {
        // Base cases
        if (n == null) return null;
        if (n.getKey().equals(key)) return n.getValue();
        // Recursive cases
        if (key.compareTo(n.getKey()) < 0){return lookup(n.getLeft(), key);}
        return lookup(n.getRight(), key);
    }

    /**
    * This method deletes the node corresponding to a given key.
    * If the key does not exist, does nothing.
    * @param key the key of the node to be deleted
    */
    public void delete(K key) {
        root = delete(root, key);
    }

    /**
    * This (recursive) helper method deletes the node corresponding to a given
    key from a given tree.
    * @param n the root of the tree
    * @param key the key of the node to be deleted
    * @return the root of the new tree
    */
    private AVLNode<K,V> delete(AVLNode<K,V> n, K key) {
        // Base case: key doesn't exist.
        if (n == null) return null;
        // If it's in the left sub-tree, go left.
        if (key.compareTo(n.getKey()) < 0) {
            n.setLeft(delete(n.getLeft(), key));
            return balance(n); // Deleting may have unbalanced tree.
        }
        // If it's in the right sub-tree, go right.
        else if (key.compareTo(n.getKey()) > 0) {
            n.setRight(delete(n.getRight(), key));
            return balance(n); // Deleting may have unbalanced tree.
        }
        // Else, we found it! Remove n.
        else {
            // 0 children
            if (n.getLeft() == null && n.getRight() == null) return null;
            // 1 child - guaranteed to be balanced.
            if (n.getLeft() == null) return n.getRight();
            if (n.getRight() == null) return n.getLeft();
            // 2 children - deleting may have unbalanced tree.
            K smallestKey = smallest(n.getRight());
            n.setKey(smallestKey);
            n.setRight(delete(n.getRight(), smallestKey));
            return balance(n);
        }
    }

    /**
    * This method returns the smallest key in a given tree.
    * @param n the root of the tree to be searched
    * @return the smallest key in the tree rooted at n
    */
    private K smallest(AVLNode<K,V> n) {
        if (n.getLeft() == null)
        return n.getKey();
        return smallest(n.getLeft());
    }

    /**
    * This method ensures that a tree rooted at a given node maintains the AVL
    tree property.
    * That is to say, the heights of the two sub-trees differ by at most 1.
    * @param n the root of the tree to be balanced
    * @return the root of the new balanced tree
    */
    // Updated balance method to enforce AVL properties, largely taken from IC7
	private AVLNode<K,V> balance(AVLNode<K,V> n) {
		if (n == null) return null;

		n.setHeight(1 + Math.max(height(n.getLeft()), height(n.getRight())));
		int balanceFactor = getBalanceFactor(n);

		// Left heavy
		if (balanceFactor > 1) {
			if (getBalanceFactor(n.getLeft()) < 0) {
				n.setLeft(rotateLeft(n.getLeft())); // Left-Right case
			}
			return rotateRight(n); // Left-Left case
		}

		// Right heavy
		if (balanceFactor < -1) {
			if (getBalanceFactor(n.getRight()) > 0) {
				n.setRight(rotateRight(n.getRight())); // Right-Left case
			}
			return rotateLeft(n); // Right-Right case
		}

		return n;
	}

	// Computes balance factor of a node
	private int getBalanceFactor(AVLNode<K,V> n) {
		return height(n.getLeft()) - height(n.getRight());
	}

	// Method for Right Rotation
	private AVLNode<K,V> rotateRight(AVLNode<K,V> y) {
		AVLNode<K,V> x = y.getLeft();
		AVLNode<K,V> z = x.getRight();

		x.setRight(y);
		y.setLeft(z);

		y.setHeight(1 + Math.max(height(y.getLeft()), height(y.getRight())));
		x.setHeight(1 + Math.max(height(x.getLeft()), height(x.getRight())));

		return x;
	}

	// Method for Left Rotation
	private AVLNode<K,V> rotateLeft(AVLNode<K,V> y) {
		AVLNode<K,V> x = y.getRight();
		AVLNode<K,V> z = x.getLeft();

		x.setLeft(y);
		y.setRight(z);

		y.setHeight(1 + Math.max(height(y.getLeft()), height(y.getRight())));
		x.setHeight(1 + Math.max(height(x.getLeft()), height(x.getRight())));

		return x;
	}
    
    /**
    * This method will get the height of a given node.
    * If the node is null, returns a height of -1.
    * @param n an AVLNode
    * @return the node's height (-1 if null)
    */
    private int height(AVLNode<K,V> n) {
        return (n == null ? -1 : n.getHeight());
    }

    /**
    * A string representation of the tree's elements obtained with an in-order
    traversal.
    */
    public String toString() {
        return "[ " + inOrder(root) + " ]" + '\n';
    }

    /**
    * Performs an in-order traversal of the tree (recursively), returning the
    items as strings.
    * @param n the root of the tree
    * @return an in-order String representation of the tree
    */
    private String inOrder(AVLNode<K,V> n) {
        StringBuilder str = new StringBuilder();
        if(n != null) {
            inOrder(n.getLeft());
            str.append("{ " + n.getKey() + " -> " + n.getValue() + "} ");
            inOrder(n.getRight());
        }
        return str.toString();
    }

    /**
    * Returns a string representing tree in its full form, with empty nodes
    represented as "{ null }".
    * Obtained using a level-order traversal.
    * @return string representation of tree
    */
    public String levelOrder() {
        LinkedList<AVLNode<K,V>> myQueue = new LinkedList<AVLNode<K,V>>();

        myQueue.offer(root);

        String returnString = "";
        AVLNode<K,V> temp;
        int count = 0;
        int level = 0;

        while (level <= height(root)) {
            temp = myQueue.poll();
            if (temp == null) {
                returnString += " { null } ";
                myQueue.offer(null);
                myQueue.offer(null);
            }
            else {
                returnString += " { " + temp.getKey().toString() + " -> " + temp.getValue().toString() + " }";
                myQueue.offer(temp.getLeft());
                myQueue.offer(temp.getRight());
            }
            count++;
            if (count >= Math.pow(2, level)) {
                returnString += "\n";
                level++;
                count = 0;
            }
        }
        return returnString;
    }
}
