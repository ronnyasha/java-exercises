package model.road;

import model.humans.Human;
import model.vehicles.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Road {

    private final List<Vehicle<? extends Human>> carsInRoad = new ArrayList<>();

    public void addCarToRoad(Vehicle<? extends Human> car) {
        carsInRoad.add(car);
    }

    public int getCountOfHumans() {
        int count = 0;
        for (Vehicle<? extends Human> car : carsInRoad) {
            count += car.getOccupiedSeats();
        }
        return count;
    }
}
