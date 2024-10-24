package app;

import control.ServiceOrderController;
import model.ServiceOrder;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);

        ServiceOrderController SOController = new ServiceOrderController();

        int choice;
        boolean on = true;

        while(on){
            menu();
            choice = input.nextInt();
            switch (choice){
                case 1:
                    SOController.serviceOrdersList();
                    break;
                case 2:
                    SOController.serviceOrderView();
                    break;
                case 3:
                    SOController.serviceOrderEdit();
                    break;
                case 4:
                    SOController.serviceOrderForm();
                    break;
                case 5:
                    SOController.serviceOrderDelete();
                    break;
                case 6:
                    SOController.getCache().printCache();
                    break;
                case 7:
                    SOController.getSOSize();
                    break;
                case 8:
                    on = false;
                    break;
                default:
                    System.out.println("Invalid value\n");
                    break;
            }
        }
    }

    public static void menu(){
        System.out.println("------- Service Order Menu --------\n");

        System.out.println("[1] - List Service Orders");
        System.out.println("[2] - View Service Order");
        System.out.println("[3] - Edit Service Order");
        System.out.println("[4] - Sign Service Order");
        System.out.println("[5] - Delete Service Order");
        System.out.println("[6] - View Cache");
        System.out.println("[7] - Registries Counter\n");
        System.out.println("[8] - Exit\n");

        System.out.print("Choice: ");
    }
}
