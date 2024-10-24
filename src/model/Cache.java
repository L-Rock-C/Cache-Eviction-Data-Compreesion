package model;

import control.ServiceOrderController;

import java.util.LinkedList;

public class Cache {

    private AutoadjustList<ServiceOrder> table;
    private int size;
    private int quantity;
    private final int CACHE_SIZE = 30;

    public Cache(){
        this.size = CACHE_SIZE;
        this.quantity = 0;
        this.table = new AutoadjustList<ServiceOrder>();
    }

    public String listCache(){
        return table.stringList();
    }

    public void printCache(){
        System.out.println("\n------- View Cache --------\n");

        int i = 1;
        for (ServiceOrder order : table) {
            System.out.print("Index: " + i + " | ");
            i++;
            order.listShow();
        }
    }

    public ServiceOrder search(int code){
        for (ServiceOrder order : table) {
            if (order.getCode() == code) {
                return order;
            }
        }
        return null;
    }

    public void add(ServiceOrder serviceOrder){
        if(!isFull()){
            table.add(serviceOrder);
        } else{
            table.removeLast();
            quantity--;
            table.add(serviceOrder);
        }
        quantity++;
    }

    public void update(ServiceOrder serviceOrder, ServiceOrderController.ACTION action){
        switch(action){
            case VIEW:
                add(serviceOrder);
                break;
            case EDIT:
                add(serviceOrder);
                System.out.println("\nCache atualizada");
                break;
            case REMOVE:
                table.remove(serviceOrder);
                System.out.println("\nCache atualizada");
                break;
        }
    }

    public boolean isFull(){
        return quantity == CACHE_SIZE;
    }

}
