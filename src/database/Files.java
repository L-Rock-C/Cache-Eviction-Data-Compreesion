package database;

import java.io.File;

public class Files {

    private final File cacheFile = new File("src\\database\\cache.txt");
    private final File serviceOrderFile = new File("src\\database\\orders.txt");

    public File getCacheFile() {
        return cacheFile;
    }

    public File getServiceOrderFile() {
        return serviceOrderFile;
    }
}
