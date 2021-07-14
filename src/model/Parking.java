package model;

import exception.InvalidSlotNumber;
import exception.NoEmptySlotAvailable;
import exception.VehicleNotFound;
import validator.InputValidator;

import java.util.*;
import java.util.stream.Collectors;

public class Parking {
    private static Parking parkingLot;
    private int floorNumber;
    private List<Slot> slots;


    private Parking(int floorNumber) {
        this.floorNumber = floorNumber;
        this.slots = new ArrayList<>();
    }

    public static Parking getParkingFloor(int floorNumber) {
        if (parkingLot == null)
            parkingLot = new Parking(floorNumber);
        return parkingLot;
    }

    private Slot getNextEmptySlotOnFloor() throws NoEmptySlotAvailable {
        for (Slot slot : slots) {
            if (!slot.isEmpty()) {
                return slot;
            }
        }
        throw new NoEmptySlotAvailable("For floorNumber " + floorNumber + " No Empty Slot available");
    }

    public boolean createParkingSLot(int numberOfSlots) {
        if (inputValidator(InputValidator.isValidSlotNumber(numberOfSlots)) || slots.size() > 0)
            return false;

        for (int i = 1; i <= numberOfSlots; i++) {
            slots.add(new Slot(UUID.randomUUID().toString(), i));
        }
        System.out.printf("Created parking of %s slots %n", numberOfSlots);
        return true;
    }

    private boolean inputValidator(boolean validSlotNumber) {
        if (!validSlotNumber) {
            return true;
        }
        return false;
    }

    public boolean parkVehicle(Vehicle vehicle) throws NoEmptySlotAvailable {
        Slot nextEmptySlotOnFloor = getNextEmptySlotOnFloor();
        nextEmptySlotOnFloor.placeVehicle(vehicle);
        System.out.printf("Car with vehicle registration number '%s' has been parked at slot number %d \n",vehicle.getVehicleNumber(), nextEmptySlotOnFloor.getSlotNumber());
        return true;
    }

    public String unParkVehicle(int slotNumber) {
        String output;
        if (slotNumber <= 0) {
            throw new InvalidSlotNumber(String.format("%d is invalid slot number", slotNumber));
        }
        Slot slot = slots.get(slotNumber - 1);
        if (slot != null) {
          output = slot.removeVehicle();
            return String.format("Slot number %d vacated",slotNumber)+output;
        } else {
            throw new InvalidSlotNumber(String.format("Slot already vacant"));
        }
    }

    public void printStatus() {
        System.out.println("Slot No.  Registration No   Driver age");
        slots.forEach(slot->{
            if (!slots.isEmpty()) {
                Vehicle parkVehicle = slot.getParkVehicle();
                if (parkVehicle != null)
                    System.out.printf("%d          %s    %s\n", slot.getSlotNumber(), parkVehicle.getVehicleNumber(), parkVehicle.getDriverAge());
            }
        });
    }

    public List<String> getVehicleNumbersByAge(String age) {
        List<String> vehicleNumbers = new ArrayList<>();
        slots.forEach(slot->{
            if (slot.isEmpty() && slot.getParkVehicle().getDriverAge().equalsIgnoreCase(age)) {
                vehicleNumbers.add(slot.getParkVehicle().getVehicleNumber());
            }
        });
        if (vehicleNumbers.isEmpty()) {
            throw new VehicleNotFound(String.format("Vehicle not found %s", age));
        }
        return vehicleNumbers;
    }

    public List<Integer> getSlotNumbersByAge(String age) {
        List<Integer> slotNumbers = this.slots.stream()
                .filter(slot->slot.isEmpty() && slot.getParkVehicle().getDriverAge().equalsIgnoreCase(age)).map(Slot::getSlotNumber)
                .collect(Collectors.toList());
        if (slotNumbers.isEmpty()) {
            throw new VehicleNotFound(String.format("Vehicle not found for Driver age %s", age));
        }
        return slotNumbers;
    }

    public Integer getSlotNumberByVehicleNumber(String vehicleNumber) {
        Optional<Integer> slotOptional = slots.stream()
                .filter(slot->slot.getParkVehicle().getVehicleNumber().equalsIgnoreCase(vehicleNumber)).map(Slot::getSlotNumber)
                .findAny();
        return slotOptional.orElseThrow(()->new VehicleNotFound(String.format("Provided vehicle number %s is not present", vehicleNumber)));
    }

}