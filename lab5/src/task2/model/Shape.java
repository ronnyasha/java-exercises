package task2.model;

import java.io.Serializable;
import java.util.Objects;

public abstract class Shape implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String color;

    protected Shape(String color) {
        this.color = Objects.requireNonNull(color, "color");
    }

    public String getColor() { return color; }

    public abstract double calcArea();

    @Override
    public String toString() {
        return "%s{color='%s', area=%.2f}".formatted(getClass().getSimpleName(), color, calcArea());
    }
}
