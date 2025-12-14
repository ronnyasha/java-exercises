package model;

public final class Rectangle extends Shape {
    private final double width;
    private final double height;

    public Rectangle(String color, double width, double height) {
        super(color);
        if (width <= 0 || height <= 0) throw new IllegalArgumentException("width/height must be > 0");
        this.width = width;
        this.height = height;
    }

    @Override
    public double calcArea() {
        return width * height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing Rectangle: width=" + width + ", height=" + height + ", color=" + getShapeColor());
    }

    @Override
    public String toString() {
        return "Rectangle{color='%s', width=%.2f, height=%.2f, area=%.2f}"
                .formatted(getShapeColor(), width, height, calcArea());
    }
}
