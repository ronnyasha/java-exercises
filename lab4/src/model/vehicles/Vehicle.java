package model.vehicles;

import model.exceptions.NoFreeSeatsException;
import model.exceptions.PassengerNotFoundException;
import model.humans.Human;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Vehicle<P extends Human> {

    private final int maxSeats;
    private final List<P> passengers = new ArrayList<>();

    protected Vehicle(int maxSeats) {
        if (maxSeats <= 0) {
            throw new IllegalArgumentException("maxSeats must be > 0");
        }
        this.maxSeats = maxSeats;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public int getOccupiedSeats() {
        return passengers.size();
    }

    public List<P> getPassengers() {
        return Collections.unmodifiableList(passengers);
    }

    public void board(P passenger) {
        if (passengers.size() >= maxSeats) {
            throw new NoFreeSeatsException("No free seats in " + getClass().getSimpleName());
        }
        passengers.add(passenger);
    }

    public void unboard(P passenger) {
        if (!passengers.remove(passenger)) {
            throw new PassengerNotFoundException(
                    passenger + " is not in " + getClass().getSimpleName()
            );
        }
    }
}
