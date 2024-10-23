package model;

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
        String listCacheReturn = listCache();
        System.out.println(listCacheReturn);
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
            table.add(serviceOrder);
        }
    }

    public boolean isFull(){
        return quantity == CACHE_SIZE;
    }

}
