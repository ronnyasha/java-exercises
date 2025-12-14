package controller;

import model.Shape;
import model.ShapeComparators;
import model.ShapeType;
import view.ShapeView;

import java.util.Arrays;

public final class ShapeController {
    private final ShapeView view;

    public ShapeController(ShapeView view) {
        this.view = view;
    }

    public void run(Shape[] shapes) {
        view.printTitle("Initial data");
        view.printShapes(shapes);

        view.printTitle("Draw all shapes");
        for (Shape s : shapes) s.draw();

        view.printTitle("Total area (all)");
        view.printTotalArea(calcTotalArea(shapes));

        view.printTitle("Total area by type");
        for (ShapeType type : ShapeType.values()) {
            double sum = calcTotalAreaByType(shapes, type);
            view.printTotalAreaByType(type.name(), sum);
        }

        view.printTitle("Sort by area (ascending)");
        Shape[] byArea = Arrays.copyOf(shapes, shapes.length);
        Arrays.sort(byArea, ShapeComparators.BY_AREA_ASC);
        view.printShapes(byArea);

        view.printTitle("Sort by color (ascending)");
        Shape[] byColor = Arrays.copyOf(shapes, shapes.length);
        Arrays.sort(byColor, ShapeComparators.BY_COLOR_ASC);
        view.printShapes(byColor);
    }

    public double calcTotalArea(Shape[] shapes) {
        double sum = 0.0;
        for (Shape s : shapes) sum += s.calcArea();
        return sum;
    }

    public double calcTotalAreaByType(Shape[] shapes, ShapeType type) {
        double sum = 0.0;
        for (Shape s : shapes) {
            if (matchesType(s, type)) sum += s.calcArea();
        }
        return sum;
    }

    private boolean matchesType(Shape shape, ShapeType type) {
        return switch (type) {
            case RECTANGLE -> shape.getClass().getSimpleName().equals("Rectangle");
            case TRIANGLE  -> shape.getClass().getSimpleName().equals("Triangle");
            case CIRCLE    -> shape.getClass().getSimpleName().equals("Circle");
        };
    }
}
