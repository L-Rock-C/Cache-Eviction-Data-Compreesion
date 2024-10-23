package control;

import model.Cache;
import model.HashTable;
import model.ServiceOrder;

import java.io.*;

public class FilesController {

    public void WriteFile(File path, String input) throws IOException
    {
        BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter(path) );
        bufferedWriter.append(input);
        bufferedWriter.close();
    }

    public HashTable readSOFile(File path) throws IOException {
        HashTable servicesOrders = new HashTable(100);

        BufferedReader bufferedReader = new BufferedReader( new FileReader(path) );

        StringBuffer sbResult = new StringBuffer();
        String line = "";

        while (line != null)
        {
            sbResult.append(line + "\n");
            line = bufferedReader.readLine();

            if(line != null)
            {
                String[] SOData = line.split(";");
                int id = Integer.parseInt(SOData[0]);
                String name = SOData[1];
                String client = SOData[2];
                String description = SOData[3];
                long requestTime = Long.parseLong(SOData[4]);

                ServiceOrder serviceOrder = new ServiceOrder(id, name, client, description, requestTime);

                servicesOrders.add(serviceOrder);
            }
        }

        return servicesOrders;
    }

    public Cache readCacheFile(File path) throws IOException {
        Cache cacheSO = new Cache();

        BufferedReader bufferedReader = new BufferedReader( new FileReader(path) );

        StringBuffer sbResult = new StringBuffer();
        String line = "";

        while (line != null)
        {
            sbResult.append(line + "\n");
            line = bufferedReader.readLine();

            if(line != null)
            {
                String[] SOData = line.split(";");
                int id = Integer.parseInt(SOData[0]);
                String name = SOData[1];
                String client = SOData[2];
                String description = SOData[3];
                long requestTime = Long.parseLong(SOData[4]);

                ServiceOrder serviceOrder = new ServiceOrder(id, name, client, description, requestTime);

                cacheSO.add(serviceOrder);
            }
        }

        return cacheSO;
    }
}
