public class SinglyLinkedList<E>{

    private static class Node<E>{
        private E data;
        private Node<E> next;
        public Node(E e, Node<E> n){
            data = e;
            next = n;
        }
        public E getElement(){
            return data;
        }
        public Node<E> getNext(){
            return next;
        }
        public void setNext(Node<E> n){
            next = n;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    // Default constructor for SinglyLinkedList creates empty list with null references
    public SinglyLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        if (size == 0)
        {
            return true;
        }
        else {
            return false;
        }
    }

    public E first(){
        return head.getElement();
    }

    public E last(){
        return tail.getElement();
    }

    public void addFirst(E element){
        if (isEmpty() == true) {
            Node<E> currentNode = new Node<E>(element, head);
            head = currentNode;
            tail = currentNode;
            size++;
        }
        else {
            Node<E> currentNode = new Node<E>(element, head);
            head = currentNode;
            size++;
        }
    }

    public void addLast(E element){
        if (isEmpty() == true) {
            Node<E> currentNode = new Node<E>(element, null);
            head = currentNode;
            tail = currentNode;
            size++;
        }
        else {
            Node<E> currentNode = new Node<E>(element, null);
            tail.setNext(currentNode);
            tail = currentNode;
            size++;
        }
    }

    public E removeFirst(){
        if (isEmpty() == true) return null;
        else {
            E returnedData = head.getElement();
            head = head.getNext();
            size--;
            return returnedData;
        }
    }
}