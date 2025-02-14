package Ben_Fisher_IC5;

public class DoublyLinkedListDeletion {
// Java program implementing a Doubly Linked List with all essential operations
    class Node {
        int data;
        Node prev, next;
        // Constructor
        Node(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
        }
        }

        private Node head, tail;
        // Constructor initializes an empty list
        // Insert at the beginning
        public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        if (head == null) {
        head = tail = newNode;
        } else {
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
        }
        }
        // Insert at the end
        public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (tail == null) {
        head = tail = newNode;
        } else {
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        }
        }
        // Insert at a specific position
        public void insertAtPosition(int data, int position) {
        if (position < 1) {
        System.out.println("Invalid position!");
        return;
        }
        Node newNode = new Node(data);
        if (position == 1) {
        insertAtBeginning(data);
        return;
        }
        Node current = head;
        int count = 1;
        while (current != null && count < position) {
        current = current.next;
        count++;
        }
        if (current == null) {
        insertAtEnd(data);
        } else {
        newNode.next = current;
        newNode.prev = current.prev;
        if (current.prev != null) {
        current.prev.next = newNode;
        }
        current.prev = newNode;
        }
    }

    public int removeBeginning(){
        if(head == null){
            System.out.println("Empty");
            return -1;
        }
        else{
            int num = head.data;
            head.next.prev = null;
            head = head.next;
            return num;
        }
    }

    public int removeEnd(){
        if(tail == null){
            System.out.println("Empty");
            return -1;
        }
        else{
            int num = tail.data;
            tail.prev.next = null;
            tail = tail.prev;
            return num;
        }

    }

    public int removeAtPosition(int position){
        if(position<0){
            System.out.println("Index out of bounds");
            return -1;
        }
        else{
            int n = 0;
            Node newN = head;
            while(newN.next!=null && n < position){
                newN = newN.next;
                n++;
            }
            return newN.data;
        }
    }
}
