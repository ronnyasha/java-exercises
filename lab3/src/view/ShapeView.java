package view;

import model.Shape;

public final class ShapeView {

    public void printTitle(String title) {
        System.out.println("\n=== " + title + " ===");
    }

    public void printShapes(Shape[] shapes) {
        for (int i = 0; i < shapes.length; i++) {
            System.out.printf("%2d) %s%n", i + 1, shapes[i]);
        }
    }

    public void printTotalArea(double area) {
        System.out.printf("Total area: %.2f%n", area);
    }

    public void printTotalAreaByType(String typeName, double area) {
        System.out.printf("Total area of %s: %.2f%n", typeName, area);
    }

    public void printMessage(String msg) {
        System.out.println(msg);
    }
}
