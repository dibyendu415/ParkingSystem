package commands;

import exception.NoEmptySlotAvailable;
import model.Parking;
import model.Vehicle;

interface CommandI {
    Parking floor = Parking.getParkingFloor(1);
    void executeCommand(String[] details);
}

public enum Command implements CommandI {

    Create_parking_lot() {
        @Override
        public void executeCommand(String[] details) {
            floor.createParkingSLot(Integer.parseInt(details[1]));
        }
    },

    Park {
        @Override
        public void executeCommand(String[] details) {
            try {
                floor.parkVehicle(new Vehicle(details[1], details[3]));
            } catch (NoEmptySlotAvailable noEmptySlotAvailable) {
                System.out.println("Sorry, parking lot is full");
            }
        }
    },
    Leave {
        @Override
        public void executeCommand(String[] details) {
            try {
                String output = floor.unParkVehicle(Integer.parseInt(details[1]));
                System.out.println(output);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    },
    Status {
        @Override
        public void executeCommand(String[] details) {
            floor.printStatus();

        }
    },
    Vehicle_registration_number_for_driver_of_age {
        @Override
        public void executeCommand(String[] details) {
            floor.getVehicleNumbersByAge(details[1]).forEach(vehicleNumber->{
                System.out.print(vehicleNumber + ", ");
            });
            System.out.println();
        }
    },

    Slot_numbers_for_driver_of_age {
        @Override
        public void executeCommand(String[] details) {
            floor.getSlotNumbersByAge(details[1]).forEach(num->System.out.print(num + ","));
            System.out.println();
        }
    },
    Slot_number_for_car_with_number {
        @Override
        public void executeCommand(String[] details) {
            try {
                System.out.println(floor.getSlotNumberByVehicleNumber(details[1]));
            } catch (Exception e) {
                System.out.println("Not Found");
            }
        }
    },
    exit() {
        @Override
        public void executeCommand(String[] details) {

        }
    }
}