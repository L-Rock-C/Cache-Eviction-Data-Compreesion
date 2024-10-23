package model;

import java.util.Date;

public class ServiceOrder {
    private int code;
    private String OSName;
    private String clientName;
    private String OSDescription;
    private long OSRequest;

    // Create Service Order object to load from database
    public ServiceOrder(int code, String OSName, String clientName, String OSDescription, long OSRequest) {
        this.code = code;
        this.OSName = OSName;
        this.clientName = clientName;
        this.OSDescription = OSDescription;
        this.OSRequest = OSRequest;
    }

    // Create Service Order object in execution
    public ServiceOrder(int code, String OSName, String clientName, String OSDescription) {
        this.code = code;
        this.OSName = OSName;
        this.clientName = clientName;
        this.OSDescription = OSDescription;
        this.OSRequest = System.currentTimeMillis();
    }

    public ServiceOrder() { }

    public int getCode() {
        return code;
    }

    public String getOSName() {
        return OSName;
    }

    public String getClientName() {
        return clientName;
    }

    public String getOSDescription() {
        return OSDescription;
    }

    public long getOSRequest() {
        return OSRequest;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setOSName(String OSName) {
        this.OSName = OSName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setOSDescription(String OSDescription) {
        this.OSDescription = OSDescription;
    }

    public void setOSRequest(long OSRequest) {
        this.OSRequest = OSRequest;
    }

    @Override
    public String toString() {
        return code + ";" + OSName + ";" + clientName + ";" + OSDescription + ";" + OSRequest + "\n";
    }

    public void listShow() {

        String requestMin = String.valueOf((OSRequest / (1000 * 60)) % 60); // Get minutes
        String requestHour = String.valueOf((OSRequest / (1000 * 60 * 60)) % 24); // Get minutes

        System.out.print("  Code: " + getCode() + "  |  ");
        System.out.print("Name: " + getOSName() + "  |  ");
        System.out.print("Client: " + getClientName() + "  |  ");
        System.out.print("Description: " + getOSDescription() + "  |  ");
        System.out.println("Request Time: " + requestHour + ":" + requestMin);

    }
}