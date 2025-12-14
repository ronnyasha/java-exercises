package model;

public final class Triangle extends Shape {
    private final double base;
    private final double height;

    public Triangle(String color, double base, double height) {
        super(color);
        if (base <= 0 || height <= 0) throw new IllegalArgumentException("base/height must be > 0");
        this.base = base;
        this.height = height;
    }

    @Override
    public double calcArea() {
        return 0.5 * base * height;
    }

    @Override
    public void draw() {
        System.out.println("Drawing Triangle: base=" + base + ", height=" + height + ", color=" + getShapeColor());
    }

    @Override
    public String toString() {
        return "Triangle{color='%s', base=%.2f, height=%.2f, area=%.2f}"
                .formatted(getShapeColor(), base, height, calcArea());
    }
}
