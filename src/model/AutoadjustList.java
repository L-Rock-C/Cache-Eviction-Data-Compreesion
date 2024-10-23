package model;

public class AutoadjustList<T> extends MyLinkedList<T> {

    public void add(T element) {
        if (this.contains(element)) {
            this.remove(element);
        }
        this.addFirst(element);
    }

}