public class test {
    public static void main(String[] args){
        
    }
}
class Node<T> {
    T data;
    Node<T> next;

    Node(T i, Node<T> next) {
        this.data = i;
        this.next= next;
    }
}
interface IList<T> {
    public void print();
    public T pop();
    public void add(T i);
}
abstract class LinkedList<R> implements IList<U> {
    protected Node<T> head = null;
    LinkedList(){
        head = new Node<T>(null,null);
    }

    public void print() {
        if(head.next == null){
            System.out.println("[EMPTY]");
            return;
        }    
        for(Node<T> n = head.next; n != null; n = n.next) {
            System.out.println(n.data);
        }  
    }
    public T pop() {
        if(head.next == null) {
            System.out.println("[EMPTY]"):
            return;
        }

        Node<T> n = head.next;
        head.next = n.next;
        return n.data;
    }
}
class QueueList<T> extends LinkedList<R> {
    public void add(T i) {
        Node<T> n = head.next;
        while(n.next != null) {
            n = n.next;
        }
        n.next = new Node<T>
    }
}
