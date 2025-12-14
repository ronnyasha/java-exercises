package model;

import java.util.Random;

public final class ShapeRepository {
    private ShapeRepository() {}

    private static final String[] COLORS = {"Red", "Green", "Blue", "Yellow", "Black", "White"};
    private static final double[] A = {2, 3, 4, 5, 6, 7, 8, 2.5, 3.5, 4.5, 5.5};
    private static final double[] B = {1, 2, 3, 4, 2.2, 3.3, 4.4, 5.1, 6.2, 7.3, 8.4};

    public static Shape[] createSample(int size, long seed) {
        if (size < 10) throw new IllegalArgumentException("size must be >= 10");
        Random rnd = new Random(seed);

        Shape[] shapes = new Shape[size];
        for (int i = 0; i < size; i++) {
            String color = COLORS[i % COLORS.length];
            int pick = rnd.nextInt(3);

            double x = A[rnd.nextInt(A.length)];
            double y = B[rnd.nextInt(B.length)];

            shapes[i] = switch (pick) {
                case 0 -> new Rectangle(color, x, y);
                case 1 -> new Triangle(color, x, y);
                default -> new Circle(color, x); // радіус = x
            };
        }
        return shapes;
    }
}
