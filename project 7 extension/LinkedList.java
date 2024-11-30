/*
 * Name: Maya Kalenak
 * 
 * Purpose: Create LinkedLists for stack and queue
 * 
 */

import java.util.ListIterator;
import java.util.Comparator;
import java.util.Iterator;

public class LinkedList<T> implements Queue<T>, Stack<T>, Iterable<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;
    protected T o1;
    protected T o2;

    // constructor
    // initialize variables
    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // return size of linked list
    public int size() {
        return size;
    }

    // reset/clear linked list
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // check if linked list is empty
    public boolean isEmpty() {
        if (size == 0)
            return true;
        else
            return false;
    }

    // change linked list to string output
    public String toString() {
        String text = "";

        Node<T> curr = head;

        while (curr != null) {
            text += " " + (curr.data);
            curr = curr.next;
        }

        return text;
    }

    // add item to next open value in linked list
    public void add(T item) {
        Node<T> newNode = new Node(item);

        if (isEmpty()) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            Node<T> curr = this.head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }

        size++;
    }

    public void addLast(T item) {
        Node<T> newNode = new Node(item);

        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            Node<T> curr = this.head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
            this.tail = newNode;
        }

        size++;
    }

    // see if the linked list has/contains the given input
    public boolean contains(Object o) {
        Node<T> curr = head;

        while (curr != null) {
            if (curr.data.equals(o))
                return true;
            // else
            curr = curr.next;
        }
        return false;
    }

    // return the item at the index
    public T get(int index) {
        Node<T> curr = head;

        if (index == size - 1)
            return getLast();

        if (index < 0 || index >= size) {
            System.out.println("Index out of bounds in get");
            return null;
        }

        // curr = head;
        if (index == 0)
            return head.getData();

        for (int i = 0; i < index - 1; i++) {
            curr = curr.getNext();
        }

        // System.out.println(curr);
        // System.exit(0);

        return curr.getData();
    }

    public T getLast() {
        Node<T> curr = head;
        Node<T> returnedNode = null;

        if (size == 0)
            return null;

        if (size == 1)
            curr = tail;

        else {
            while (curr.next.next != null) {
                curr = curr.next;
            }

            returnedNode = curr.next;

            this.tail = returnedNode;
        }

        return tail.data;
    }

    // remove and return the first item
    public T removeFirst() {
        Node<T> curr = head;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
        }
        size--;
        return curr != null ? curr.data : null;
    }

    public T removeLast() {
        Node<T> curr = head;
        Node<T> removedNode = null;

        while (curr.next.next != null) {
            curr = curr.next;
        }

        removedNode = curr.next;

        curr.next = null;

        this.tail = curr;

        return removedNode.data;
    }

    // add the inputed item to the given index
    public void add(int index, T item) {
        Node<T> newNode = new Node(item);
        Node<T> next = new Node(0);
        Node<T> prev = new Node(0);

        int i = 0;

        if (this.head == null) {
            this.head = newNode;
            this.head.next = null;
        } else {
            Node<T> curr = head;

            while (i != index) {
                next = curr.next;
                if (next == null)
                    break;
                prev = curr;
                curr = curr.next;
                i++;
            }

            prev.next = newNode;
            newNode.next = curr;
        }

        if (index == 0) {
            Node<T> curr = this.head;
            while (i < index) {
                curr = curr.next;
                i++;
            }
            newNode = curr;
        }

        if (index == size - 1)
            addLast(item);

        size++;
    }

    public void addFirst(T item) {
        Node<T> newNode = new Node(item);
        
        newNode.next = this.head;
        this.head = newNode;
            
        size++;
    }

    // remove the element from the given index and return it
    public T remove(int index) {
        Node<T> curr = head;
        Node<T> prev = null;

        if (index < 0 || index >= size) {
            System.out.println("Index out of bounds in remove");
            return null;
        }
        if (index == size - 1)
            return removeLast();
        if (index == 0) {
            head = head.next;

        } else {
            for (int i = 0; i < index; i++) {
                prev = curr;
                curr = curr.next;
            }
            prev.next = curr.next;
        }

        size--;
        return curr.data;
    }

    // see if the input is the same as the linked list content-wise
    public boolean equals(Object o) {
        if (!(o instanceof LinkedList)) {
            return false;
        }

        LinkedList oAsALinkedList = (LinkedList) o;

        Node<T> curr = head;
        Node<T> currList = oAsALinkedList.head;

        while (curr != null && currList != null) {
            if (!curr.data.equals(currList.data))
                return false;
            curr = curr.next;
            currList = currList.next;

            if (size() != oAsALinkedList.size())
                return false;

        }
        return true;
    }

    public Iterator iterator() {
        return new LLIterator(this.head);
    }

    private class Node<T> {
        Node<T> next;
        T data;

        public Node(T item) {
            data = item;
            next = null;
        }

        public T getData() {
            return data;
        }

        public void setNext(Node n) {
            next = n;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    private class LLIterator implements Iterator<T> {
        Node node;

        public LLIterator(Node head) {
            node = head;
        }

        public boolean hasNext() {
            while (node.next != null)
                return true;
            return false;
        }

        public T next() {
            Node<T> curr = node;
            curr = curr.next;
            return curr.getData();
        }

        public void remove() {

        }

    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();

        list.push(2);
        System.out.println(list);
        list.push(3);
        System.out.println(list);
        list.push(4);

        System.out.println(list);
        System.out.println("size: " + list.size());
        System.out.println("last item: " + list.top());

        list.pop();

        System.out.println(list);
        System.out.println("new size: " + list.size());
        System.out.println("new last item: " + list.top());
    }

    @Override
    public T top() {
        return getLast();
    }

    @Override
    public T pop() {
        // T returnObj = getLast();
        // return returnObj;

        // return remove(0);

        return removeFirst();
    }

    @Override
    public void push(T item) {
        // addLast(item);
        addFirst(item);
    }

    @Override
    public T peek() {
        T item = get(0);
        return item;
    }

    @Override
    public T poll() {
        T item = removeFirst();
        return item;
    }

    @Override
    public void offer(T item) {
        addLast(item);
    }
}
