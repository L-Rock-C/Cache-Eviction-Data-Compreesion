package model;

public class HashTable {
    private AutoadjustList<ServiceOrder>[] table;
    private int size;
    private int quantity;
    private final float CHARGE_FACTOR = 0.75F;

    @SuppressWarnings("unchecked")
    public HashTable(int initialSize) {
        this.size = initialSize;
        this.table = new AutoadjustList[initialSize];
        this.quantity = 0;
        for (int i = 0; i < initialSize; i++) {
            table[i] = new AutoadjustList<>();
        }
    }

    public int getQuantity(){
        return quantity;
    }

    public int getSize(){
        return size;
    }

    private int hashFunction(int code) {
        int prime = 127; // First prime number after 16
        return code % prime;
    }

    public void add(ServiceOrder serviceOrder) {
        int index = hashFunction(serviceOrder.getCode());

        for (ServiceOrder order : table[index]) {
            if (order.getCode() == serviceOrder.getCode()) {
                table[index].remove(order);
                break;
            }
        }

        table[index].add(serviceOrder);
        quantity++;

        if (quantity / (double) size >= CHARGE_FACTOR) {
            resize();
        }
    }

    public ServiceOrder search(int code) {
        int index = hashFunction(code);
        for (ServiceOrder order : table[index]) {
            if (order.getCode() == code) {
                return order;
            }
        }
        return null;
    }

    public boolean remove(int code) {
        int index = hashFunction(code);
        AutoadjustList<ServiceOrder> list = table[index];
        for (ServiceOrder order : list) {
            if (order.getCode() == code) {
                list.remove(order);
                quantity--;
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newSize = size * 2;
        AutoadjustList<ServiceOrder>[] newTable = new AutoadjustList[newSize];

        for (int i = 0; i < newSize; i++) {
            newTable[i] = new AutoadjustList<>();
        }

        for (AutoadjustList<ServiceOrder> list : table) {
            for (ServiceOrder order : list) {
                int newIndex = order.getCode() % newSize;
                newTable[newIndex].addFirst(order);
            }
        }

        this.size = newSize;
        this.table = newTable;
    }

    public void runHashTable(){
        for (int i = 0; i < size; i++) {
            AutoadjustList<ServiceOrder> list = table[i];

            for (ServiceOrder order : list) {
                System.out.print("Index " + i + ": ");
                order.listShow();
            }
        }
    }

    public String runHashTableString(){
        String returnString = "";
        for (int i = 0; i < size; i++) {
            AutoadjustList<ServiceOrder> list = table[i];

            for (ServiceOrder order : list) {
                returnString += order.toString();
            }
        }
        return returnString;
    }
}