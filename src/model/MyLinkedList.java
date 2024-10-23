package model;

public class MyLinkedList<T> implements Iterable<T>{
    private Node<T> head;
    private int size;

    // Subclass to represent a node
    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public MyLinkedList() {
        head = null;
        size = 0;
    }

    // Add an element at the start of the list
    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        size++;
    }

    // Add an element at the end of the list
    public void addLast(T data) {
        if (head == null) {
            addFirst(data);
            return;
        }
        Node<T> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node<>(data);
        size++;
    }

    // Remove the first element of the given data
    public boolean remove(T data) {
        if (head == null) return false;

        if (head.data.equals(data)) {
            head = head.next;
            size--;
            return true;
        }

        Node<T> current = head;
        while (current.next != null) {
            if (current.next.data.equals(data)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Remove the last element of the given data
    public boolean removeLast() {
        if (head == null) return false;

        Node<T> current = head;
        while (current.next.next != null) {
            current = current.next;
        }

        current.next = null;
        size--;

        return true;
    }

    // Verify if the list contains the given data
    public boolean contains(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Return the list size
    public int size() {
        return size;
    }

    // Print all list elements
    public void printList() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public String stringList() {
        String returnString = "";
        Node<T> current = head;
        while (current != null) {
            returnString += current.data.toString();
            current = current.next;
        }
        return returnString;
    }

    // Iterator for for-each loop
    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private Node<T> current = head;

            public boolean hasNext() {
                return current != null;
            }

            public T next() {
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}