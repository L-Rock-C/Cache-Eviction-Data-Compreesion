package control;

import database.Files;
import model.Cache;
import model.HashTable;
import model.ServiceOrder;

import java.io.IOException;
import java.util.Scanner;

public class ServiceOrderController {
    Files files = new Files();

    public enum ACTION { VIEW, EDIT, REMOVE }
    FilesController fileAccess = new FilesController();
    HashTable serviceOrders = fileAccess.readSOFile(files.getServiceOrderFile());
    Scanner input = new Scanner(System.in);
    Cache cache = fileAccess.readCacheFile(files.getCacheFile());

    public ServiceOrderController() throws IOException { }

    /**
     * Lists all Service Order in dataset
     */
    public void serviceOrdersList(){
        System.out.println("\n------- Service Orders List --------\n");

        serviceOrders.runHashTable();
        System.out.println(serviceOrders.getSize());

        System.out.println();
    }

    /**
     * View selected Service Order by its code
     */
    public void serviceOrderView() throws IOException {

        System.out.println("\n------- Service Order Visualization --------\n");

        System.out.print("Select a service order by its code to visualize: ");
        int code = input.nextInt();

          ServiceOrder SOFromCache = cache.search(code);

            if(SOFromCache != null){
                System.out.println();

                SOFromCache.listShow();

                cache.update(SOFromCache, ACTION.VIEW);
                fileAccess.WriteFile(files.getCacheFile(), cache.listCache());

                System.out.print("\n(Data from cache) \nPress Enter to continue.");
                input.nextLine();
                input.nextLine();
            } else {

            ServiceOrder chosen = serviceOrders.search(code);

            if (chosen != null) {
                System.out.println();
                chosen.listShow();

                cache.update(chosen, ACTION.VIEW);
                fileAccess.WriteFile(files.getCacheFile(), cache.listCache());

                System.out.print("\nPress Enter to continue.");
                input.nextLine();
                input.nextLine();
            } else {
                System.out.println("\nService Order with code " + code + " doesn't exist.\n");
            }
        }

    }

    /**
     * Edit selected Service Order by its code
     */
    public void serviceOrderEdit() throws IOException {
        System.out.println("------- Service Order Edit --------\n");

        serviceOrdersList();

        System.out.print("\nSelect a service order by its code to edit: ");
        int code = input.nextInt();

        ServiceOrder chosen = serviceOrders.search(code);
        if(chosen != null){
            boolean done = false;
            while(!done){
                System.out.println("\n------- Editing Service Order --------\n");

                System.out.println("[1] Code:" + chosen.getCode());
                System.out.println("[2] Name: " + chosen.getOSName());
                System.out.println("[3] Client: " + chosen.getClientName());
                System.out.println("[4] Description: " + chosen.getOSDescription());
                System.out.println("\n[5] Finish edit.\n");

                System.out.print("Select a field to edit: ");
                code = input.nextInt();
                input.nextLine();

                switch(code){
                    case 1:
                        System.out.print("Insert new Code value: ");
                        int OSCode = input.nextInt();
                        chosen.setCode(OSCode);
                        break;
                    case 2:
                        System.out.print("Insert new Name value: ");
                        String name = input.nextLine();
                        chosen.setOSName(name);
                        break;
                    case 3:
                        System.out.print("Insert new Client value: ");
                        String client = input.nextLine();
                        chosen.setClientName(client);
                        break;
                    case 4:
                        System.out.print("Insert new Description value: ");
                        String description = input.nextLine();
                        chosen.setOSDescription(description);
                        break;
                    case 5:
                        fileAccess.WriteFile(files.getServiceOrderFile(), serviceOrders.runHashTableString());
                        cache.update(chosen, ACTION.EDIT);
                        fileAccess.WriteFile(files.getCacheFile(), cache.listCache());

                        System.out.println();
                        done = true;
                        break;
                    default:
                        System.out.println("Invalid number.");
                        break;
                }
            }
        } else {
            System.out.println("\nService Order with id " + code + " doesn't exist.\n");
        }
    }

    /**
     * Sign a Service Order into database
     */
    public void serviceOrderForm() throws IOException {

        System.out.println("------- Service Order Form --------\n");

        System.out.print("Code: ");
        int code = input.nextInt();
        input.nextLine();

        System.out.print("Name: ");
        String name = input.nextLine();

        System.out.print("Client: ");
        String client = input.nextLine();

        System.out.print("Description: ");
        String description = input.nextLine();

        ServiceOrder serviceOrder = new ServiceOrder(code, name, client, description);
        serviceOrders.add(serviceOrder);

        fileAccess.WriteFile(files.getServiceOrderFile(), serviceOrders.runHashTableString());

    }

    /**
     * Delete a Service Order by its code
     */
    public void serviceOrderDelete() throws IOException {
        System.out.println("\n------- Service Order Delete --------\n");

        serviceOrdersList();

        System.out.print("\nSelect a service order by its id to delete: ");
        int code = input.nextInt();

        ServiceOrder chosen = serviceOrders.search(code);
        if(chosen != null){
            serviceOrders.remove(chosen.getCode());

            fileAccess.WriteFile(files.getServiceOrderFile(), serviceOrders.runHashTableString());

            cache.update(chosen, ACTION.REMOVE);
            fileAccess.WriteFile(files.getCacheFile(), cache.listCache());

            System.out.println("\nService order deleted.");
        }

        System.out.println();
    }

    public void getSOSize(){
        System.out.println("\n------- Service Order Delete --------\n");
        System.out.println("Quantity of registries: " + serviceOrders.getQuantity() + "\n");
    }

    public Cache getCache(){
        return cache;
    }
}
