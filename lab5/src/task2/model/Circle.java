package task2.model;

public final class Circle extends Shape {
    private static final long serialVersionUID = 1L;

    private final double r;

    public Circle(String color, double r) {
        super(color);
        if (r <= 0) throw new IllegalArgumentException("r must be > 0");
        this.r = r;
    }

    @Override public double calcArea() { return Math.PI * r * r; }

    @Override public String toString() {
        return "Circle{color='%s', r=%.2f, area=%.2f}".formatted(getColor(), r, calcArea());
    }
}
