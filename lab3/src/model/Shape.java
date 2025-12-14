package model;

import java.util.Objects;

public abstract class Shape implements Drawable {
    private final String shapeColor;

    protected Shape(String shapeColor) {
        this.shapeColor = Objects.requireNonNull(shapeColor, "shapeColor cannot be null");
    }

    public String getShapeColor() {
        return shapeColor;
    }

    public abstract double calcArea();

    @Override
    public String toString() {
        return "%s{color='%s', area=%.2f}".formatted(
                getClass().getSimpleName(), shapeColor, calcArea()
        );
    }
}
