package model;

public class Vehicle {

    private String vehicleNumber;
    private String driverAge;

    public Vehicle(String vehicleNumber, String driverAge) {
        this.vehicleNumber = vehicleNumber;
        this.driverAge = driverAge;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getDriverAge() {
        return driverAge;
    }

    public void setDriverAge(String driverAge) {
        this.driverAge = driverAge;
    }
}
