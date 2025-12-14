package app;

import model.humans.Firefighter;
import model.humans.Passenger;
import model.humans.Policeman;
import model.road.Road;
import model.vehicles.*;

public class Main {
    public static void main(String[] args) {

        Bus bus = new Bus(3);
        bus.board(new Passenger("Anna"));
        bus.board(new Firefighter("Oleh"));
        bus.board(new Policeman("Ivan"));

        Taxi taxi = new Taxi(2);
        taxi.board(new Passenger("Kate"));

        FireTruck fireTruck = new FireTruck(2);
        fireTruck.board(new Firefighter("Taras"));

        PoliceCar policeCar = new PoliceCar(1);
        policeCar.board(new Policeman("Stepan"));

        Road road = new Road();
        road.addCarToRoad(bus);
        road.addCarToRoad(taxi);
        road.addCarToRoad(fireTruck);
        road.addCarToRoad(policeCar);

        System.out.println(bus.getOccupiedSeats());
        System.out.println(taxi.getOccupiedSeats());
        System.out.println(fireTruck.getOccupiedSeats());
        System.out.println(policeCar.getOccupiedSeats());

        System.out.println("People on road: " + road.getCountOfHumans());
    }
}
