package Ben_Fisher_HW2;

import java.util.*;
import java.io.*;

public class LinkedList {
    int n;
    ListNode start;
    ListNode rear;

    public LinkedList(){
        n= 0;
        start= null;
        rear= null;
    }

    public LinkedList(int size, ListNode first, ListNode last){
        n= size;
        start= first;
        rear= last;
    }

    public static LinkedList readBigInteger(Scanner in){
        LinkedList x = new LinkedList();
        int num = readInteger(in);
        if(num==-1){return null;}
        for(int i=0; i<num; i++){
            int j = readInteger(in);
            if(j == -1){
                break;
            }
            x.start = new ListNode(j, x.start);
            if(x.rear == null){
                x.rear = x.start;
            }
            x.n++;
        }
        return x;
    }

    public void printBigInteger(){
        this.reverse(0);
        ListNode cur = start;
        while(cur != null && cur.data == 0){
            cur = cur.next;
        }
        if(cur == null){System.out.print(0);}
        while(cur != null){
            System.out.print(cur.data);
            cur = cur.next;
        }
        this.reverse(0);
        System.out.println();
    }

    public void reverse(int level){
        Test.checkList(this); // Do not remove or move this statement.

        if(n<=1){return;}
        int mid = n/2;
        ListNode cur = start;
        for(int i=1; i<mid; i++){
            cur = cur.next;
        }
        LinkedList firstHalf = new LinkedList(mid, start, cur);
        LinkedList secondHalf = new LinkedList(n-mid, cur.next, rear);
        cur.next = null;
        
        firstHalf.reverse(level + 1);
        secondHalf.reverse(level+ 1);
        
        start = secondHalf.start;
        secondHalf.rear.next = firstHalf.start;
        rear = firstHalf.rear;
    }

    public void plus_plus(){
        ListNode cur = start;
        ListNode prev = null;
        int num = 1;
        while(cur!=null && num > 0){
            int sum = cur.data + num;
            cur.data = sum % 10;
            num = sum/10;
            prev = cur;
            cur = cur.next;
        }
        if(num > 0){
            prev.next = new ListNode(1, null);
            rear = prev.next;
        }
    }

    public LinkedList plus(LinkedList y){
        LinkedList z = new LinkedList();
    
        this.reverse(0);
        y.reverse(0); 
        ListNode xNode = this.start;
        ListNode yNode = y.start;
        int num = 0;
        while(xNode != null || yNode != null || num > 0){
            int xVal;
            if(xNode != null){xVal = xNode.data;}
            else{xVal = 0;}
            int yVal;
            if(yNode != null){yVal = yNode.data;}
            else {yVal = 0;}
            int sum = xVal+yVal+num;
            num = sum/10;
            
            ListNode newNode = new ListNode(sum % 10, z.start);
            z.start = newNode;
            if(z.rear == null){z.rear = newNode;}
            z.n++;
            if(xNode != null){xNode = xNode.next;}
            if(yNode != null){yNode = yNode.next;}
        }

        z.reverse(0);
        return z;
    }

    public static int readInteger(Scanner in){
        int n;
        try{
            n = in.nextInt();
            if (n >=0) return(n);
            else return(-1);
        }
        catch(Exception e){
            // We are assuming legal integer input values are >= zero.
            return(-1);
        }
    }

    public void printList(){
        ListNode current;
        int count=0;
        current= start;
        while(current != null){
            count++;
            System.out.println("Item " + count + " in the list is " + current.data);
            current= current.next;
        }
    }
}