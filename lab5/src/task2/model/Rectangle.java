package task2.model;

public final class Rectangle extends Shape {
    private static final long serialVersionUID = 1L;

    private final double w;
    private final double h;

    public Rectangle(String color, double w, double h) {
        super(color);
        if (w <= 0 || h <= 0) throw new IllegalArgumentException("w/h must be > 0");
        this.w = w; this.h = h;
    }

    @Override public double calcArea() { return w * h; }

    @Override public String toString() {
        return "Rectangle{color='%s', w=%.2f, h=%.2f, area=%.2f}".formatted(getColor(), w, h, calcArea());
    }
}
