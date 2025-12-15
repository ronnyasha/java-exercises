package task2.model;

public final class Triangle extends Shape {
    private static final long serialVersionUID = 1L;

    private final double base;
    private final double height;

    public Triangle(String color, double base, double height) {
        super(color);
        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException("base and height must be > 0");
        }
        this.base = base;
        this.height = height;
    }

    @Override
    public double calcArea() {
        return 0.5 * base * height;
    }

    @Override
    public String toString() {
        return "Triangle{color='%s', base=%.2f, height=%.2f, area=%.2f}"
                .formatted(getColor(), base, height, calcArea());
    }
}
