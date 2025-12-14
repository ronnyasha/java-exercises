package model.vehicles;

import model.humans.Human;

public class Taxi extends Car<Human> {
    public Taxi(int maxSeats) {
        super(maxSeats);
    }
}
