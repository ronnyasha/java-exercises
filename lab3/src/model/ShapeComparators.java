package model;

import java.util.Comparator;

public final class ShapeComparators {
    private ShapeComparators() {}

    public static final Comparator<Shape> BY_AREA_ASC =
            Comparator.comparingDouble(Shape::calcArea);

    public static final Comparator<Shape> BY_COLOR_ASC =
            Comparator.comparing(Shape::getShapeColor, String.CASE_INSENSITIVE_ORDER)
                    .thenComparingDouble(Shape::calcArea); // щоб порядок був стабільніший
}
