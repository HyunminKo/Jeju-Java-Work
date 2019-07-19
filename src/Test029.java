class Node {
    int data = 0;
    Node next = null;

    Node(int i, Node n){
        this.data = i;
        this.next = n;
    }
}
class LinkedList {
    Node head = null;
    Node tail = null;

    LinkedList(){
        this.head = new Node(0,null);
        this.tail = this.head;
    }
    void add(int num) {
        tail.next = new Node(num,null);
        tail = tail.next;
    }
    void print(){
        for(Node t = head.next ; t != null; t = t.next){
            System.out.println(t.data);
        }
    }
}
public class Test029{
    public static void main(String[] args){
        LinkedList l = new LinkedList();
        l.add(10);
        l.add(20);
        l.print();
    }
}