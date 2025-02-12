package Ben_Fisher_IC4;

import java.util.LinkedList;
import java.util.Stack;

public class IsAPalindrome {
    private class Node{
        public int data;
        public Node next;
        public Node(int d, Node n) {data=d; next=n;}
    }

    public boolean isPalindrome(Node head){
        boolean val = true;
        Stack<Integer> sta = new Stack<>();
        Stack<Integer> rev = new Stack<>();
        Node iter = head;
        while(iter.next != null){
            sta.push(iter.data);
            iter = iter.next;
        }
        sta.add(iter.data);
        for(int i=sta.size()-1; i>=0; i++){
            rev.push(sta.get(i));
        }
        while(!sta.isEmpty()){
            if(sta.peek()!=rev.peek()){
                val = false;
            }
            sta.pop();
            rev.pop();
        }
        return val;
    }
}
