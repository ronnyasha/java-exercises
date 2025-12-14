package model.vehicles;

import model.humans.Human;

public abstract class Car<P extends Human> extends Vehicle<P> {
    protected Car(int maxSeats) {
        super(maxSeats);
    }
}
