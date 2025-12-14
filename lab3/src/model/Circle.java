package model;

public final class Circle extends Shape {
    private final double radius;

    public Circle(String color, double radius) {
        super(color);
        if (radius <= 0) throw new IllegalArgumentException("radius must be > 0");
        this.radius = radius;
    }

    @Override
    public double calcArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing Circle: radius=" + radius + ", color=" + getShapeColor());
    }

    @Override
    public String toString() {
        return "Circle{color='%s', radius=%.2f, area=%.2f}"
                .formatted(getShapeColor(), radius, calcArea());
    }
}
