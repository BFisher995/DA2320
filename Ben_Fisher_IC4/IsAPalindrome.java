package Ben_Fisher_IC4;

import java.util.LinkedList;

public class IsAPalindrome {
    private class Node{
        public int data;
        public Node next;
        public Node(int d, Node n) {data=d; next=n;}
    }

    public boolean isPalindrome(LinkedList<Integer> lst){
        boolean val = true;
        LinkedList<Integer> rev = new LinkedList<>();
        for(int i=lst.size()-1; i>=0; i++){
            rev.add(lst.get(i));
        }
        for(int j=0; j<lst.size(); j++){
            if(lst.get(j)!=rev.get(j)){
                val = false;
            }
        }
        return val;
    }

    public boolean isPalindrome(Node n){
        boolean val = true;
        return val;
    }
}
